package br.com.hisig.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hisig.modules.user.dto.AuthUserDTO;
import br.com.hisig.modules.user.dto.AuthUserResponseDTO;
import br.com.hisig.modules.user.useCases.AuthUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class AuthUserController {
  
  @Autowired
  private AuthUserUseCase authUserUseCase;

  @PostMapping("/auth")
  @Tag(name = "Dados do Usuário")
  @Operation(summary = "Login do usuário no sistema", description = "Essa funções é reponsavel por fazer o login do usuário informado o email e senha e retornando um token de acesso")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = AuthUserResponseDTO.class)))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário não encotrado")
  })
  public ResponseEntity<Object> create(@RequestBody AuthUserDTO authUserDTO) {
    
    try {
      var result = this.authUserUseCase.execute(authUserDTO);
      
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

  }
  
}
