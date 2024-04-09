package br.com.hisig.modules.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record AuthAdminRequestDTO (
  @Schema(example = "joaomariasilva@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do administrador")
  String email, 
  @Schema(example = "1234567890", requiredMode = RequiredMode.REQUIRED, description = "Senha do administrador")
  String password
) {
  
}
