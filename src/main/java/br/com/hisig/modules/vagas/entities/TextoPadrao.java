package br.com.hisig.modules.vagas.entities;

import br.com.hisig.modules.vagas.dto.texto.TextoDto;
import br.com.hisig.modules.vagas.dto.texto.TextoPadraoAtualizarDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Textos_Padrao")
public class TextoPadrao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    public TextoPadrao(TextoDto textoDto) {
        this.texto = textoDto.texto();
    }

    public void atualizarInforma(TextoPadraoAtualizarDto dados) {
        if (dados.texto() != null) {
            this.texto = dados.texto();
        }
    }
}
