package br.com.hisig.modules.user.useCases;

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

import br.com.hisig.modules.user.dto.AuthUserDTO;
import br.com.hisig.modules.user.dto.AuthUserResponseDTO;
import br.com.hisig.modules.user.repositories.UserRepository;

@Service
public class AuthUserUseCase {

  @Value("${security.token.secret.user}")
  private String secretKey;
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthUserResponseDTO execute(AuthUserDTO authUserDTO) throws AuthenticationException {
    var user = this.userRepository.findByEmail(authUserDTO.getEmail()).orElseThrow(
      () -> {
        throw new UsernameNotFoundException("User/password incorrect");
      }
    );

    // Verificar se as senha são iguais
    var passwordMatches = this.passwordEncoder.matches(authUserDTO.getPassword(), user.getPassword());

    // Se não for iguais -> Erro
    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    // Se for iguais -> Gerar o token
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expiresIn = Instant.now().plus(Duration.ofMinutes(30));

    var token = JWT.create()
        .withIssuer("hisigvagas")
        .withSubject(user.getId().toString())
        .withClaim("roles", Arrays.asList("USER"))
        .withExpiresAt(expiresIn)
        .sign(algorithm);
    
    var AuthUserResponse = AuthUserResponseDTO.builder()
      .access_token(token)
      .expires_in(expiresIn.toEpochMilli())
      .build();

    return AuthUserResponse;
  }
}
