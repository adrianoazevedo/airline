package br.com.hrzon.airline.infra.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "login", length = 50, nullable = false, unique = true)
  private String login;

  @Column(name = "senha", nullable = false)
  private String password;

  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

}
