package br.com.hisig.modules.admin.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.hisig.modules.admin.dto.AuthAdminDTO;
import br.com.hisig.modules.admin.dto.AuthAdminResponseDTO;
import br.com.hisig.modules.admin.useCases.AuthAdminUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/admin")
public class AuthAdminController {

  @Autowired
  private AuthAdminUseCase authAdminUseCase;

  @PostMapping("/auth")
  @Tag(name = "Admin")
  @Operation(summary = "Login do administrador no sistema", description = "Essa funções é reponsavel por fazer o login do administrador informado o email e senha e retornando um token de acesso")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = AuthAdminResponseDTO.class)))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário não encotrado")
  })
  public ResponseEntity<Object> create(@RequestBody AuthAdminDTO authAdminDTO) {
    try {
      var result = this.authAdminUseCase.execute(authAdminDTO);

      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
