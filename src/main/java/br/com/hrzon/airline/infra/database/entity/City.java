package br.com.hrzon.airline.infra.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "cidade")
@Data
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "nome", length = 100, nullable = false)
  private String name;

  @Column(name = "uf", length = 2, nullable = false)
  private String uf;

}
