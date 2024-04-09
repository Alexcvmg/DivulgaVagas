package br.com.hisig.modules.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthAdminMasterDTO {
  
  @Schema(example = "joaomariasilva", requiredMode = RequiredMode.REQUIRED, description = "username do administrador master")
  private String username;

  @Schema(example = "1234567890", requiredMode = RequiredMode.REQUIRED, description = "Senha do administrador master")
  private String password;
}
