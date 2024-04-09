package br.com.hisig.modules.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import br.com.hisig.modules.user.dto.ProfileUserResponseDTO;
import br.com.hisig.modules.user.dto.UpdateProfileUserDTO;
import br.com.hisig.modules.user.entities.UserEntity;
import br.com.hisig.modules.user.useCases.ProfileUserUseCase;
import br.com.hisig.modules.user.useCases.UpdateProfileUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private ProfileUserUseCase profileUserUseCase;

  @Autowired
  private UpdateProfileUserUseCase updateProfileUserUseCase;

  @GetMapping("/")
  @PreAuthorize("hasRole('USER')")
  @Tag(name = "Dados do Usuário", description = "Informações do Usuário")
  @Operation(summary = "Exibir informações do perfil do usuário", description = "Essa funções é reponsavel por informar os dados de perfil do usuário")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = ProfileUserResponseDTO.class)))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário não encotrado")
  })
  public ResponseEntity<Object> get(HttpServletRequest request) {

    var idUser = request.getAttribute("user_id");

    try {
      var profile = this.profileUserUseCase
          .execute(UUID.fromString(idUser.toString()));

      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/")
  @PreAuthorize("hasRole('USER')")
  @Tag(name = "Dados do Usuário", description = "Atualiza Informações do perfil Usuário")
  @Operation(summary = "Editar perfil do usuário", description = "Essa funções é reponsavel por fazer alterações no registro dos dados de perfil do usuário")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = ProfileUserResponseDTO.class)))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário não encotrado")
  })
  public ResponseEntity<Object> update(@Valid @RequestBody UpdateProfileUserDTO updateProfileUserDTO,
      HttpServletRequest request) {
    try {
      var userId = request.getAttribute("user_id");

      var userEntity = UserEntity.builder()
          .id(UUID.fromString(userId.toString()))
          .email(updateProfileUserDTO.getEmail())
          .job(updateProfileUserDTO.getJob())
          .jobRole(updateProfileUserDTO.getJobRole())
          .officeHours(updateProfileUserDTO.getOfficeHours())
          .build();

      var result = this.updateProfileUserUseCase.execute(userEntity);

      return ResponseEntity.ok().body(result);
    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
