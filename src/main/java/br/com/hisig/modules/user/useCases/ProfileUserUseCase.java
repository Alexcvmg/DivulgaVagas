package br.com.hisig.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hisig.modules.user.dto.ProfileUserResponseDTO;
import br.com.hisig.modules.user.repositories.UserRepository;

@Service
public class ProfileUserUseCase {
  
  @Autowired
  private UserRepository userRepository;

  public ProfileUserResponseDTO execute(UUID idUser) {

    UUID userId = idUser;

    if (userId == null) {
      throw new IllegalArgumentException("Admin ID cannot be null");
    }

    var user = this.userRepository.findById(userId)
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("User not found");
        });

    var userDTO = ProfileUserResponseDTO.builder()
        .officeHours(user.getOfficeHours())
        .jobRole(user.getJobRole())
        .job(user.getJob())
        .name(user.getName())
        .email(user.getEmail())
        .id(user.getId())
        .build();

    return userDTO;
  }
}
