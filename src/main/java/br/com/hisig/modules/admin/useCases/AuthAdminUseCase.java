package br.com.hisig.modules.admin.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.hisig.modules.admin.dto.AuthAdminDTO;
import br.com.hisig.modules.admin.dto.AuthAdminResponseDTO;
import br.com.hisig.modules.admin.repositories.AdminRepository;

@Service
public class AuthAdminUseCase {

  @Value("${security.token.secret.admin}")
  private String secretKey;

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthAdminResponseDTO execute(AuthAdminDTO authAdminDTO) throws AuthenticationException {
    var admin = this.adminRepository.findByEmail(authAdminDTO.getEmail()).orElseThrow(
      () -> {
        throw new UsernameNotFoundException("Username/password incorrect");
      }
    );
    
    // Verificar a senha são iguais
    var passowrdMatches = this.passwordEncoder.matches(authAdminDTO.getPassword(), admin.getPassword());

    // Se não for igual -> Erro
    if (!passowrdMatches) {
      throw new AuthenticationException();
    }

    // Se for igual -> Gerar o token
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expiresIn = Instant.now().plus(Duration.ofMinutes(30));

    var token = JWT.create().withIssuer("javagas")
        .withExpiresAt(expiresIn)
        .withSubject(admin.getId().toString())
        .withClaim("roles", Arrays.asList("ADMIN"))
        .sign(algorithm);

    var authAdminResponseDTO = AuthAdminResponseDTO.builder()
        .access_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .build();

    return authAdminResponseDTO;
  }
}
