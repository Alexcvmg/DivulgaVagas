package br.com.hisig.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hisig.modules.user.entities.UserEntity;
import br.com.hisig.modules.user.repositories.UserRepository;
import br.com.hisig.utils.Utils;

@Service
public class UpdateProfileUserUseCase {

  @Autowired
  private UserRepository userRepository;
  
  public UserEntity execute( UserEntity userEntity ) {
    
    UUID userId = userEntity.getId();

    if (userId == null) {
      throw new IllegalArgumentException("Token Inválida");
    }

    var user = this.userRepository.findById(userId).orElseThrow(() -> {
      throw new UsernameNotFoundException("Usuário não encontrado");
    });

    Utils.copyNonNullProperties(userEntity, user);

    return this.userRepository.save(user);
  }
}
