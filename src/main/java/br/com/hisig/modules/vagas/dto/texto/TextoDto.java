package br.com.hisig.modules.vagas.dto.texto;

import jakarta.validation.constraints.NotBlank;

public record TextoDto(@NotBlank String texto) {
}
