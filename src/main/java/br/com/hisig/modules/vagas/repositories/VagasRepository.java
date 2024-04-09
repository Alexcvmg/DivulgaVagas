package br.com.hisig.modules.vagas.repositories;

import br.com.hisig.modules.vagas.entities.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {
    @Query("select v from Vagas v where v.tituloDaVaga = :titulo")
    Vagas listarPorNomeDaVaga(String titulo);
}
