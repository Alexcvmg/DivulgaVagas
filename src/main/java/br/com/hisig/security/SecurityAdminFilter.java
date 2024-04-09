package br.com.hisig.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.hisig.providers.JWTAdminProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityAdminFilter extends OncePerRequestFilter{

  @Autowired
  private JWTAdminProvider jwtAdminProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    
    String header = request.getHeader("Authorization");

    // Verifica se a requisição é para /admin ou /registration
    if (request.getRequestURI().startsWith("/admin") || request.getRequestURI().startsWith("/registration")) {
      if (header != null) {
        
        // Lógica de verificação do token JWT
        var token = this.jwtAdminProvider.validateToken(header);
        
        if (token == null) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }

        var roles = token.getClaim("roles").asList(Object.class);

        var grants = roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
            .toList();

        request.setAttribute("admin_id", token.getSubject());
        UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);

        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }

    filterChain.doFilter(request, response);
  }
  
}

