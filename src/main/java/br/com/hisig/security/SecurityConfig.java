package br.com.hisig.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private SecurityAdminMasterFilter securityAdminMasterFilter;

  @Autowired
  private SecurityUserFilter securityUserFilter;

  @Autowired
  private SecurityAdminFilter securityAdminFilter;

  private static final String[] SWAGGER_LIST = {
    "/swagger-ui/**",
    "/v3/api-docs/**",
    "/swagger-resource/**",
  };
  
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(auth -> {
      auth//.requestMatchers("/user/registration").permitAll()
          .requestMatchers("/user/auth").permitAll()
          .requestMatchers("/admin/").permitAll()
          .requestMatchers("/admin/auth").permitAll()
          .requestMatchers("/master/admin/auth").permitAll()
          .requestMatchers(SWAGGER_LIST).permitAll();
      auth.anyRequest().authenticated();
    })
    .addFilterBefore(securityAdminMasterFilter, BasicAuthenticationFilter.class)
    .addFilterBefore(securityAdminFilter, BasicAuthenticationFilter.class)
    .addFilterBefore(securityUserFilter, BasicAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
