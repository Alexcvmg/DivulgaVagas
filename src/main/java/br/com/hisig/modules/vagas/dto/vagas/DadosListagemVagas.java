package br.com.hisig.modules.vagas.dto.vagas;

import br.com.hisig.modules.vagas.entities.TextoPadrao;
import br.com.hisig.modules.vagas.entities.Vagas;

import java.util.List;

public record DadosListagemVagas(Long id, String nome, String data, List<TextoPadrao> textos) {
    public DadosListagemVagas(Vagas vaga){
        this(vaga.getId(), vaga.getTituloDaVaga(), vaga.getDataFormatada(), vaga.getTextos());
    }
}
