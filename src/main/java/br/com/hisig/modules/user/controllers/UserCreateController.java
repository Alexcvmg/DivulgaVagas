package br.com.hisig.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.hisig.modules.user.entities.UserEntity;
import br.com.hisig.modules.user.useCases.CreateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/registration")
public class UserCreateController {
  @Autowired
  private CreateUserUseCase createUserUseCase;

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @Tag(name = "Admin", description = "Funcionalidades para Administrador")
  @Operation(summary = "Criar usuário padrão do sistema", description = "Essa funções é reponsavel por fazer o cadastro dos usuários padrão no sistema")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário não encotrado")
  })
  public ResponseEntity<Object> create(@Valid @RequestBody UserEntity userEntity) {
    try {
      
      var result = this.createUserUseCase.execute(userEntity);

      return ResponseEntity.ok().body(result);
    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
                                                                         