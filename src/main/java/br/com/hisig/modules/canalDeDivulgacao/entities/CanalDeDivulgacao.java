package br.com.hisig.modules.canalDeDivulgacao.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.hisig.modules.canalDeDivulgacao.dto.DtoCanalDeDivulgacaoRequest;
import br.com.hisig.modules.canalDeDivulgacao.dto.DtoCanalDeDivulgacaoResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "CANAL_DIVULGACAO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CanalDeDivulgacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PK_CANALDEDIVULGACAO")
    private UUID id;

    @CreationTimestamp
    @Column(name = "DT_CRIADO")
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "DT_ATUALIZADO")
    private LocalDateTime atualizadoEm;

    @NotBlank
    @Column(name = "NM_NOMEDOCANAL", unique = true)
    private String nomeDoCanal;

    // @OneToOne
    // private Vaga vaga;

    public CanalDeDivulgacao(DtoCanalDeDivulgacaoRequest canalDeDivulgacaoRequest) {
        this.id = canalDeDivulgacaoRequest.getId();
        this.nomeDoCanal = canalDeDivulgacaoRequest.getNomeDoCanal();
    }

    public CanalDeDivulgacao(DtoCanalDeDivulgacaoResponse canalDeDivulgacaoResponse) {
        this.id = canalDeDivulgacaoResponse.getId();
        this.nomeDoCanal = canalDeDivulgacaoResponse.getNomeDoCanal();
    }

}
