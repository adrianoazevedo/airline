package br.com.hrzon.airline.infra.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "aeroporto")
@Data
public class Airport {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "nome", length = 250, nullable = false)
  private String name;

  @Column(name = "iata", length = 100, nullable = false, unique = true)
  private String iata;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_cidade", referencedColumnName = "id")
  private City city;

}
