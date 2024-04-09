package br.com.hisig.modules.vagas.Service;


import br.com.hisig.modules.vagas.dto.vagas.DadosListagemVagas;
import br.com.hisig.modules.vagas.dto.vagas.VagasAtualizarDto;
import br.com.hisig.modules.vagas.dto.vagas.VagasDto;
import br.com.hisig.modules.vagas.entities.Vagas;
import br.com.hisig.modules.vagas.repositories.VagasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VagaService {
    @Autowired
    private VagasRepository repository;

    public void salvar(VagasDto dados){

        repository.save(new Vagas(dados));
    }

    public Page<DadosListagemVagas> listarAll(Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemVagas::new);
    }

    public void atualizar(VagasAtualizarDto dadosUpdate){
        Vagas vaga = repository.getById(dadosUpdate.id());
        vaga.atualizarInforma(dadosUpdate);
    }

    public void excluir(Long id){

        repository.deleteById(id);
    }

    public Vagas buscarPorNome(String nome){
      return repository.listarPorNomeDaVaga(nome);
    }
}
