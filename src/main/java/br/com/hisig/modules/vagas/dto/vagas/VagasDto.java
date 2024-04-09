package br.com.hisig.modules.vagas.dto.vagas;

import br.com.hisig.modules.vagas.entities.TextoPadrao;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record VagasDto(@NotBlank String tituloDaVaga, List<TextoPadrao> textos) {
}
