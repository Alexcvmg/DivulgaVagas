package br.com.hisig.modules.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import br.com.hisig.modules.admin.dto.DeleteAdminRequestDTO;
import br.com.hisig.modules.admin.dto.ListAdminDTO;
import br.com.hisig.modules.admin.entities.AdminEntity;
import br.com.hisig.modules.admin.useCases.CreateAdminUseCase;
import br.com.hisig.modules.admin.useCases.DeleteAdminUseCase;
import br.com.hisig.modules.admin.useCases.ListAllAdminUseCase;
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


@RestController
@RequestMapping("/master/admin")
@Tag(name = "Admin Master", description = "Funcionalidades para Administrador Master")
public class AdminMasterController {
  
  @Autowired
  private CreateAdminUseCase createAdminUseCase;

  @Autowired
  private DeleteAdminUseCase deleteAdminUseCase;

  @Autowired
  private ListAllAdminUseCase listAllAdminUseCase;
  
  // Criar um administrador no sistema 
  @PostMapping("/registration")
  @PreAuthorize("hasRole('MASTER_ADMIN')")
  @Operation(summary = "Criar um usuário administrador", description = "Essa funções é reponsavel por realizar a criação dos usuários administrador no sistema")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = AdminEntity.class)))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário não encotrado")
  })
  public ResponseEntity<Object> create(@Valid @RequestBody AdminEntity adminEntity) {

    try {
      var result = this.createAdminUseCase.execute(adminEntity);

      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // Deleta o administrador do sistema informando o ID do administrador
  @DeleteMapping("/")
  @PreAuthorize("hasRole('MASTER_ADMIN')")
  @Operation(summary = "Deleta usuário administrador", description = "Essa funções é reponsavel por realizar o delete do registro do usuário administrador")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = AdminEntity.class)))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário não encotrado")
  })
  public ResponseEntity<Object> deleteAdmin(@Valid @RequestBody DeleteAdminRequestDTO deleteAdminRequestDTO, HttpServletRequest request) {
    try {

      var result = this.deleteAdminUseCase.execute(deleteAdminRequestDTO);
      
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // Lista todos os usuários ADMIN
  @GetMapping("/list-admin")
  @Operation(summary = "Lista dos administrador", description = "Essa funções é reponsavel por informar a listagem dos dados dos administradores registrados no sistema")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = ListAdminDTO.class)))
    })
  })
  public List<ListAdminDTO> getAllAdmin() {
    
    return this.listAllAdminUseCase.execute();
  }
  

}
