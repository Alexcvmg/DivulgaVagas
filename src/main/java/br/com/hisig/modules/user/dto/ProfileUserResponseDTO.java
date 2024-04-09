package br.com.hisig.modules.user.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUserResponseDTO {
  
  private UUID id;

  @Schema(example = "joaomariasilva@gmail.com")
  private String email;

  @Schema(example = "João Maria da Silva")
  private String name;

  @Schema(example = "Desenvolvedor Web - Front-end")
  private String job;

  @Schema(example = "Estágiario")
  private String jobRole;

  @Schema(example = "Matutino")
  private String officeHours;
  
}
