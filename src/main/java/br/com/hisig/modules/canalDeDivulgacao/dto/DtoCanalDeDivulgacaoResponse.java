package br.com.hisig.modules.canalDeDivulgacao.dto;

import java.util.UUID;

import org.springframework.lang.NonNull;

import br.com.hisig.modules.canalDeDivulgacao.entities.CanalDeDivulgacao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCanalDeDivulgacaoResponse {

	private UUID id;

	@NonNull
    @Schema(example = "linkedin")
	private String nomeDoCanal;

    public DtoCanalDeDivulgacaoResponse(CanalDeDivulgacao canalDeDivulgacao) {
        this.id = canalDeDivulgacao.getId();
        this.nomeDoCanal = canalDeDivulgacao.getNomeDoCanal();
    }
}