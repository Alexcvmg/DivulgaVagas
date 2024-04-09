package br.com.hisig.modules.vagas.Service;

import br.com.hisig.modules.vagas.dto.texto.DadosListagemTextoPadrao;
import br.com.hisig.modules.vagas.dto.texto.TextoDto;
import br.com.hisig.modules.vagas.dto.texto.TextoPadraoAtualizarDto;
import br.com.hisig.modules.vagas.entities.TextoPadrao;
import br.com.hisig.modules.vagas.entities.Vagas;
import br.com.hisig.modules.vagas.repositories.TextoPadraoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TextoPadraoService {
    @Autowired
    private TextoPadraoRepository textoPadraoRepository;

    public void salvar(TextoDto dados){

        textoPadraoRepository.save(new TextoPadrao(dados));
    }

    public Page<DadosListagemTextoPadrao> listarAll(Pageable paginacao){
        return textoPadraoRepository.findAll(paginacao).map(DadosListagemTextoPadrao::new);
    }

    public void atualizar(TextoPadraoAtualizarDto dadosUpdate){
        TextoPadrao textoPadrao = textoPadraoRepository.getById(dadosUpdate.id());
        textoPadrao.atualizarInforma(dadosUpdate);
    }

    public void excluir(Long id){

        textoPadraoRepository.deleteById(id);
    }

    public TextoPadrao buscarTextoPorId(Long id){

        return textoPadraoRepository.getTextoPorId(id);
    }
}
