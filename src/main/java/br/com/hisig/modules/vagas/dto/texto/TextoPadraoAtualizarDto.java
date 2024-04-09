package br.com.hisig.modules.vagas.dto.texto;

import jakarta.validation.constraints.NotNull;

public record TextoPadraoAtualizarDto(@NotNull Long id, String texto) {

}
