package br.com.hisig.modules.canalDeDivulgacao.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.hisig.modules.canalDeDivulgacao.dto.DtoCanalDeDivulgacaoResponse;
import br.com.hisig.modules.canalDeDivulgacao.useCases.CanalDeDivulgacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/user/canaldevagasuser")
@Tag(name = "Canal de vaga user", description = "Funcionalidades para usuários comuns")
public class CanalDeDivulgacaoControlerUser {

    @Autowired
    private CanalDeDivulgacaoService canalDeDivulgacaoService;

    @GetMapping
    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "lista os canais de divulgação de vagas", description = "Essa funções é reponsavel por listar os canais de vagas no sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = DtoCanalDeDivulgacaoResponse.class)))
        }),
        @ApiResponse(responseCode = "400", description = "Canais de divulgação não encontrados.")
    })
    public ResponseEntity<List<DtoCanalDeDivulgacaoResponse>> listarCanais() {
        try {
            return ResponseEntity.ok().body(canalDeDivulgacaoService.retornarListaDeCanaLDeDivulgacao());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("X-Error-Message", e.getMessage()).body(null);
        }
    }

    @GetMapping(value = "/{nomeDoCanal}")
    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Busca o canal de divulgação de vagas", description = "Essa funções é reponsavel por buscar um canal de vagas no sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = DtoCanalDeDivulgacaoResponse.class)))
        }),
        @ApiResponse(responseCode = "400", description = "Canal de divulgação não encontrado.")
    })
    public ResponseEntity<DtoCanalDeDivulgacaoResponse> buscarCanalDeDivulgacao(@PathVariable String nomeDoCanal) {
        try {
            return ResponseEntity.ok().body(canalDeDivulgacaoService.mostrarCanalDeDivulgacaoDeVagas(nomeDoCanal));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
