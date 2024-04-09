package br.com.hisig.modules.canalDeDivulgacao.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.hisig.modules.canalDeDivulgacao.dto.DtoCanalDeDivulgacaoRequest;
import br.com.hisig.modules.canalDeDivulgacao.dto.DtoCanalDeDivulgacaoResponse;
import br.com.hisig.modules.canalDeDivulgacao.entities.CanalDeDivulgacao;
import br.com.hisig.modules.canalDeDivulgacao.repositories.CanalDeDivulgacaoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CanalDeDivulgacaoService {

    @Autowired
    private CanalDeDivulgacaoRepository canalDeDivulgacaoRepository;

    public DtoCanalDeDivulgacaoResponse criarCanalDeDivulgacaoDeVagas(
            DtoCanalDeDivulgacaoRequest canalDeDivulgacaoRequest) {
        verificaExixtenciaCanalPorNome(canalDeDivulgacaoRequest.getNomeDoCanal());
        CanalDeDivulgacao canalDeDivulgacao = new CanalDeDivulgacao(canalDeDivulgacaoRequest);
        canalDeDivulgacaoRepository.save(canalDeDivulgacao);
        DtoCanalDeDivulgacaoResponse canalDeDivulgacaoResponse = new DtoCanalDeDivulgacaoResponse(canalDeDivulgacao);
        return canalDeDivulgacaoResponse;
    }

    public List<DtoCanalDeDivulgacaoResponse> retornarListaDeCanaLDeDivulgacao() {
        try {
            List<CanalDeDivulgacao> listaOrigem = canalDeDivulgacaoRepository.findAll();
            List<DtoCanalDeDivulgacaoResponse> listaDestino = new ArrayList<>();

            for (CanalDeDivulgacao elemento : listaOrigem) {
                DtoCanalDeDivulgacaoResponse elementoConvertido = new DtoCanalDeDivulgacaoResponse(elemento.getId(),
                        elemento.getNomeDoCanal());
                listaDestino.add(elementoConvertido);
            }
            return listaDestino;
        } catch (Exception e) {
            throw new EntityNotFoundException("Canais de divulgação não encontrados");
        }
    }

    public void deletarCanalDeDivulgacaoDeVagas(String nomeDoCanal) {
        buscaExistenciaCanal(nomeDoCanal);
        canalDeDivulgacaoRepository.findByNomeDoCanal(nomeDoCanal);
    }

    public DtoCanalDeDivulgacaoResponse mostrarCanalDeDivulgacaoDeVagas(String nomeDoCanal) {
        buscaExistenciaCanal(nomeDoCanal);
        CanalDeDivulgacao canalDeDivulgacao = canalDeDivulgacaoRepository.findByNomeDoCanal(nomeDoCanal).get();
        DtoCanalDeDivulgacaoResponse canalDeDivulgacaoResponse = new DtoCanalDeDivulgacaoResponse(canalDeDivulgacao);

        return canalDeDivulgacaoResponse;
    }

    public DtoCanalDeDivulgacaoResponse atualizarCanalDeDivulgacao(
            DtoCanalDeDivulgacaoRequest canalDeDivulgacaoRequest) {
        buscaExistenciaCanalPorId(canalDeDivulgacaoRequest.getId());
        CanalDeDivulgacao canalDeDivulgacaoExistente = canalDeDivulgacaoRepository
                .findById(canalDeDivulgacaoRequest.getId()).get();
        canalDeDivulgacaoExistente.setNomeDoCanal(canalDeDivulgacaoRequest.getNomeDoCanal());
        canalDeDivulgacaoRepository.save(canalDeDivulgacaoExistente);
        DtoCanalDeDivulgacaoResponse canalDeDivulgacaoResponse = new DtoCanalDeDivulgacaoResponse(
                canalDeDivulgacaoExistente);

        return canalDeDivulgacaoResponse;
    }

    private void buscaExistenciaCanal(String nomeDoCanal) {
        if (!canalDeDivulgacaoRepository.existsByNomeDoCanal(nomeDoCanal)) {
            throw new EntityExistsException("Esse canal de divulgação não existe");
        }
    }

    private void buscaExistenciaCanalPorId(UUID id) {
        Optional<CanalDeDivulgacao> canalOptional = canalDeDivulgacaoRepository.findById(id);
        if (!canalOptional.isPresent()) {
            throw new EntityExistsException("Esse canal de divulgação não existe");
        }
    }

    private void verificaExixtenciaCanalPorNome(String nomeDoCanal) {
        if (canalDeDivulgacaoRepository.existsByNomeDoCanal(nomeDoCanal)) {
            throw new EntityExistsException("Esse canal de divulgação ja existe");
        }
    }
}
