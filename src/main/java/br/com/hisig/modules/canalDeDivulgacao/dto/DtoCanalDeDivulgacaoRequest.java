package br.com.hisig.modules.canalDeDivulgacao.dto;

import java.util.UUID;

import org.springframework.lang.NonNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCanalDeDivulgacaoRequest {

	private UUID id;

	@NonNull
	@Schema(example = "linkedin")
	private String nomeDoCanal;

}