package br.com.hisig.modules.admin.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hisig.modules.admin.entities.AdminEntity;
import br.com.hisig.modules.admin.repositories.AdminRepository;
import br.com.hisig.utils.Utils;

@Service
public class UpdateProfileAdminUseCase {

  @Autowired
  private AdminRepository adminRepository;

  public AdminEntity execute( AdminEntity adminEntity ){

    UUID adminId = adminEntity.getId();

    if (adminId == null) {
      throw new IllegalArgumentException("Admin ID cannot be null");
    }

    var admin = this.adminRepository.findById(adminId).orElseThrow(() -> {
      throw new UsernameNotFoundException("Admin não foi encontrado");
    });

    Utils.copyNonNullProperties(adminEntity, admin);

    return this.adminRepository.save(admin);
  }
}
