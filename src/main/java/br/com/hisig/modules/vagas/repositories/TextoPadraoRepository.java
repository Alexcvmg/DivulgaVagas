package br.com.hisig.modules.vagas.repositories;

import br.com.hisig.modules.vagas.entities.TextoPadrao;
import br.com.hisig.modules.vagas.entities.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TextoPadraoRepository extends JpaRepository<TextoPadrao, Long> {
    @Query("select t from TextoPadrao t where t.id = :id")
    TextoPadrao getTextoPorId(Long id);
}
