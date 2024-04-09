package br.com.hisig.modules.admin.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hisig.modules.admin.dto.ListAdminDTO;
import br.com.hisig.modules.admin.entities.AdminEntity;
import br.com.hisig.modules.admin.repositories.AdminRepository;

@Service
public class ListAllAdminUseCase {
  
  @Autowired
  private AdminRepository adminRepository;

  public List<ListAdminDTO> execute() {
    var listAdmin = adminRepository.findAll();
    
    return listAdmin.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  private ListAdminDTO convertToDTO(AdminEntity adminEntity) {
    var adminDTO = ListAdminDTO.builder()
    .id(adminEntity.getId())
    .name(adminEntity.getName())
    .email(adminEntity.getEmail())
    .createdAt(adminEntity.getCreatedAt())
    .updatedAt(adminEntity.getUpdatedAt())
    .build();
    
    return adminDTO;
  }
}
