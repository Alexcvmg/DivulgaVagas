package br.com.hisig.modules.admin.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hisig.modules.admin.entities.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {
  Optional<AdminEntity> findByEmail(String email);
}
