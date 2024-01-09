package br.com.hrzon.airline.infra.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "passageiro")
@Data
public class Passenger {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "nome", length = 50, nullable = false)
  private String name;

  @Column(name = "cpf", length = 11, nullable = false, unique = true)
  private String cpf;

  @Column(name = "data_nascimento", nullable = false)
  private LocalDate birthday;

  @CreationTimestamp
  @Column(name = "data_criacao", columnDefinition = "TIMESTAMP", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "data_atualizacao", columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY)
  private List<Ticket> tickets;

}
