package br.com.hisig.modules.admin.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.hisig.modules.admin.dto.AuthAdminMasterDTO;
import br.com.hisig.modules.admin.dto.AuthAdminResponseDTO;

@Service
public class AuthAdminMasterUseCase {

  @Value("${security.admin.secret.password}")
  private String masterPassword;

  @Value("${security.admin.secret.username}")
  private String masterUsername;

  @Value("${security.token.secret.admin}")
  private String secretKey;

  public AuthAdminResponseDTO execute(AuthAdminMasterDTO authAdminMasterDTO) throws AuthenticationException {
    
    // Se não forem iguais -> Erro
    if (!isAdminMaster(authAdminMasterDTO)) {
      throw new AuthenticationException();
    }

    // Se for igual -> Gerar o token
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expiresIn = Instant.now().plus(Duration.ofMinutes(30));

    var token = JWT.create().withIssuer("javagas")
        .withExpiresAt(expiresIn)
        .withClaim("roles", Arrays.asList("MASTER_ADMIN"))
        .sign(algorithm);

    var authAdminResponseDTO = AuthAdminResponseDTO.builder()
        .access_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .build();

    return authAdminResponseDTO;
  }

  // Verifica se senha e username são iguais
  private boolean isAdminMaster(AuthAdminMasterDTO authAdminMasterDTO) {
    return authAdminMasterDTO.getUsername().equals(masterUsername) && authAdminMasterDTO.getPassword().equals(masterPassword);
  }
}
