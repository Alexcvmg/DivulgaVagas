package br.com.hisig.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserDTO {
  
  @Schema(example = "joaomariasilva@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do usuário")
  private String email;

  @Schema(example = "1234567890", requiredMode = RequiredMode.REQUIRED, description = "Senha do usuário")
  private String password;
}
