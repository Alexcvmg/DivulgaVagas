package br.com.hisig.modules.vagas.dto.texto;

import br.com.hisig.modules.vagas.entities.TextoPadrao;


public record DadosListagemTextoPadrao(Long id, String texto) {
    public DadosListagemTextoPadrao(TextoPadrao texto){
        this(texto.getId(), texto.getTexto());
    }
}
