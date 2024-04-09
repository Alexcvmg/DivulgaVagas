package br.com.hisig.modules.vagas.controllers;

import br.com.hisig.modules.vagas.Service.TextoPadraoService;
import br.com.hisig.modules.vagas.Service.VagaService;
import br.com.hisig.modules.vagas.dto.vagas.DadosListagemVagas;
import br.com.hisig.modules.vagas.dto.vagas.VagasAtualizarDto;
import br.com.hisig.modules.vagas.dto.vagas.VagasDto;
import br.com.hisig.modules.vagas.entities.TextoPadrao;
import br.com.hisig.modules.vagas.entities.Vagas;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/admin/hising10/vagas")
@SecurityRequirement(name = "jwt_auth")
public class VagasController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private TextoPadraoService textoPadraoService;

    @GetMapping
    public Page<DadosListagemVagas> listarVagas(@PageableDefault(size = 10) Pageable paginacao){
        return vagaService.listarAll(paginacao);
    }

    @GetMapping("/{nome}")
    public Vagas listarVagasPorNome(@PathVariable String nome){
        return vagaService.buscarPorNome(nome);
    }

    @PostMapping
    @Transactional
    public void cadastrarVaga(@RequestBody @Valid VagasDto dados){
        vagaService.salvar(dados);
    }

    @PutMapping()
    @Transactional
    public void atualizarVaga(@RequestBody @Valid VagasAtualizarDto dadosUpdate){

        vagaService.atualizar(dadosUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirVaga(@PathVariable Long id){

        vagaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    public TextoPadrao adicionarTextoNaVaga(@PathVariable Long id){
       return textoPadraoService.buscarTextoPorId(id);
    }
}
