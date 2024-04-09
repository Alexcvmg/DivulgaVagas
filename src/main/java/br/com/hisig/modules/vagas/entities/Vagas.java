package br.com.hisig.modules.vagas.entities;

import br.com.hisig.modules.vagas.dto.vagas.VagasAtualizarDto;
import br.com.hisig.modules.vagas.dto.vagas.VagasDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "vagas")
@EqualsAndHashCode(of = "id")
public class Vagas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome da vaga")
    private String tituloDaVaga;
    @Transient
    private LocalDateTime atualizadoEm;
    @Transient
    private LocalDateTime criadaEm;
    @Transient
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    @Column(name = "criado em")
    private String dataFormatada;
    @OneToMany
    @JoinColumn(name = "vaga_id")
    private List<TextoPadrao> textos = new ArrayList<>();

    public Vagas(String titulo){
        this.tituloDaVaga = titulo;
        this.criadaEm = LocalDateTime.now();
        this.dataFormatada = criadaEm.format(formato);

    }
    public Vagas(VagasDto dados){
        this.tituloDaVaga = dados.tituloDaVaga();
        this.textos = dados.textos();
        this.criadaEm = LocalDateTime.now();
        this.dataFormatada = criadaEm.format(formato);
    }

    public void atualizarInforma(VagasAtualizarDto dados) {
        if(dados.nome() != null){
            this.tituloDaVaga = dados.nome();
        }
    }

    public void adicionarTextoPadrao(TextoPadrao texto){
        textos.add(texto);
    }
}
