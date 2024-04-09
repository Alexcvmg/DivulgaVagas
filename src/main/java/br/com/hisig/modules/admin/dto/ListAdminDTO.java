
package br.com.hisig.modules.admin.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListAdminDTO {

  private UUID id;

  @Schema(example = "joaomariasilva@gmail.com", requiredMode = RequiredMode.REQUIRED) 
  private String email;

  @Schema(example = "João Maria da Silva", requiredMode = RequiredMode.REQUIRED)
  private String name;
  
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  
}
