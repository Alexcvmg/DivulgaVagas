package br.com.hisig.modules.canalDeDivulgacao.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.hisig.modules.canalDeDivulgacao.entities.CanalDeDivulgacao;

public interface CanalDeDivulgacaoRepository extends JpaRepository<CanalDeDivulgacao, UUID> {
    boolean existsByNomeDoCanal(String nomeDoCanal);

    Optional<CanalDeDivulgacao> findByNomeDoCanal(String nomeDoCanal);
}
