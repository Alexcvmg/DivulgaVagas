package br.com.hisig.modules.admin.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hisig.exceptions.UserFoundException;
import br.com.hisig.modules.admin.entities.AdminEntity;
import br.com.hisig.modules.admin.repositories.AdminRepository;

@Service
public class CreateAdminUseCase {

  @Autowired
  private AdminRepository adminRepository;

  @Autowired 
  private PasswordEncoder passwordEncoder;

  public AdminEntity execute(AdminEntity adminEntity){
    this.adminRepository.findByEmail(adminEntity.getEmail()).ifPresent((admin) -> {
      throw new UserFoundException();
    });

    var password = passwordEncoder.encode(adminEntity.getPassword());
    adminEntity.setPassword(password);

    return this.adminRepository.save(adminEntity);
  }
}
