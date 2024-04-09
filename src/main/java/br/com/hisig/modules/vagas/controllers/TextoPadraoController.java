package br.com.hisig.modules.vagas.controllers;

import br.com.hisig.modules.vagas.Service.TextoPadraoService;
import br.com.hisig.modules.vagas.dto.texto.DadosListagemTextoPadrao;
import br.com.hisig.modules.vagas.dto.texto.TextoDto;
import br.com.hisig.modules.vagas.dto.texto.TextoPadraoAtualizarDto;
import br.com.hisig.modules.vagas.entities.TextoPadrao;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/hising10/divulgacao")
@SecurityRequirement(name = "jwt_auth")
@PreAuthorize("hasRole('ADMIN')")
public class TextoPadraoController {

    @Autowired
    private TextoPadraoService textoPadraoService;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTextoPadrao(@RequestBody @Valid TextoDto dados){
        textoPadraoService.salvar(dados);
        return ResponseEntity.ok().body(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTextoPadrao>>  listarTextos(@PageableDefault(size = 10) Pageable paginacao){
        var page = textoPadraoService.listarAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity atualizarTextoPadrao(@RequestBody @Valid TextoPadraoAtualizarDto dadosUpdate){
        textoPadraoService.atualizar(dadosUpdate);
        return ResponseEntity.ok(dadosUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTextoPadrao(@PathVariable Long id){

        textoPadraoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
