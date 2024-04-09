package br.com.hisig.modules.admin.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hisig.modules.admin.dto.ProfileAdminResponseDTO;
import br.com.hisig.modules.admin.dto.UpdateProfileAdminDTO;
import br.com.hisig.modules.admin.entities.AdminEntity;
import br.com.hisig.modules.admin.useCases.ProfileAdminUseCase;
import br.com.hisig.modules.admin.useCases.UpdateProfileAdminUseCase;
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

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin", description = "Funcionalidades para Administrador")
public class AdminController {

  @Autowired
  private ProfileAdminUseCase profileAdminUseCase;

  @Autowired 
  private UpdateProfileAdminUseCase updateProfileUseCase;

  // Rota para ver o perfil do Admin
  @GetMapping("/")
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "Exibir informações do perfil do administrador", description = "Essa funções é reponsavel por informar os dados de perfil do administrador")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = ProfileAdminResponseDTO.class)))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário não encotrado")
  })
  public ResponseEntity<Object> get(HttpServletRequest request) {
    
    var idUser = request.getAttribute("admin_id");

    try {
      var profile = this.profileAdminUseCase
          .execute(UUID.fromString(idUser.toString()));
          
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // Rota para Atualizar o perfil do Admin
  @PutMapping("/")
  @Operation(summary = "Editar perfil do administrador", description = "Essa funções é reponsavel por fazer alterações no registro dos dados de perfil do administrador")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = ProfileAdminResponseDTO.class)))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário não encotrado")
  })
  public ResponseEntity<Object> update(@Valid @RequestBody UpdateProfileAdminDTO updateProfileAdminDTO, HttpServletRequest request) {
    try {
      var adminId = request.getAttribute("admin_id");

      var adminEntity = AdminEntity.builder()
          .id(UUID.fromString(adminId.toString()))
          .email(updateProfileAdminDTO.getEmail())
          .job(updateProfileAdminDTO.getJob())
          .jobRole(updateProfileAdminDTO.getJobRole())
          .officeHours(updateProfileAdminDTO.getOfficeHours())
          .build();
      
      var result = updateProfileUseCase.execute(adminEntity);

      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
