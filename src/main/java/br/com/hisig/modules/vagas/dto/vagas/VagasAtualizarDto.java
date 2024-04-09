package br.com.hisig.modules.vagas.dto.vagas;

import jakarta.validation.constraints.NotNull;

public record VagasAtualizarDto(@NotNull Long id, String nome) {

}
