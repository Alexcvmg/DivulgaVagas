package br.com.hisig.modules.admin.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hisig.modules.admin.dto.ProfileAdminResponseDTO;
import br.com.hisig.modules.admin.repositories.AdminRepository;

@Service
public class ProfileAdminUseCase {

  @Autowired
  private AdminRepository adminRepository;

  public ProfileAdminResponseDTO execute( UUID idAdmin ) {
    
    UUID adminId = idAdmin;

    if (adminId == null) {
      throw new IllegalArgumentException("Admin ID cannot be null");
    }

    var admin = this.adminRepository.findById(adminId)
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("User not found");
        });

    var adminDTO = ProfileAdminResponseDTO.builder()
        .officeHours(admin.getOfficeHours())
        .jobRole(admin.getJobRole())
        .job(admin.getJob())
        .name(admin.getName())
        .email(admin.getEmail())
        .id(admin.getId())
        .build();

    return adminDTO;
  }
  
}
