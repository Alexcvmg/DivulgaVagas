package br.com.hisig.modules.user.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Pattern(regexp = "\\S+", message = "O campo [name] não deve conter espaço em branco")
  @Schema(example = "João Maria da Silva", requiredMode = RequiredMode.REQUIRED)
  private String name;

  @Email(message = "O campo [email] deve conter um e-mail válido")
  @Column(unique = true)
  @Schema(example = "joaomariasilva@gmail.com", requiredMode = RequiredMode.REQUIRED)
  private String email;

  @Length(min = 10)
  private String password;

  @Schema(example = "Desenvolvedor Web - Front-end", requiredMode = RequiredMode.NOT_REQUIRED)
  private String job;

  @Schema(example = "Estágiario")
  private String jobRole;

  @Schema(example = "Matutino")
  private String officeHours;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
