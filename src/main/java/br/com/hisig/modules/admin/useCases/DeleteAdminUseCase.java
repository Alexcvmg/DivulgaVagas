package br.com.hisig.modules.admin.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hisig.modules.admin.dto.DeleteAdminRequestDTO;
import br.com.hisig.modules.admin.entities.AdminEntity;
import br.com.hisig.modules.admin.repositories.AdminRepository;

@Service
public class DeleteAdminUseCase {
  
  @Autowired
  private AdminRepository adminRepository;

  public AdminEntity execute( DeleteAdminRequestDTO deleteAdminRequestDTO ) {
    
    UUID adminId = deleteAdminRequestDTO.id();

    if (adminId == null) {
      throw new IllegalArgumentException("Admin ID cannot be null");
    } 

    var admin = this.adminRepository.findById(adminId)
    .orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found");
    });

    this.adminRepository.deleteById(adminId);
    return admin;
  }
}
