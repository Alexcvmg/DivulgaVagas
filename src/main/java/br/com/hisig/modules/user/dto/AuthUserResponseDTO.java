package br.com.hisig.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserResponseDTO {

  @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJoaXNpZ3ZhZ2FzIiwic3ViIjoiMjA2OWM4OWEtMjM0OS00YWM1LTk0NzAtM2M5YjY1NzBhYTExIiwicm9sZXMiOlsiVVNFUiJdLCJleHAiOjE3MDgzNzYxMzB9.FmPXbqU9hTemE-cJMw73g7nhCK7Vd-xQdzU8XUEwN2E")
  private String access_token;
  
  @Schema(example = "1708376130704")
  private Long expires_in;
}
