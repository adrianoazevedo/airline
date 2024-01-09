package br.com.hrzon.airline.infra.database.entity;

import br.com.hrzon.airline.domain.enums.StatusEnum;
import br.com.hrzon.airline.infra.database.converter.StatusConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "voo")
@Data
public class Flight {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "numero", length = 50, nullable = false)
  private String number;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_aeroporto_origem", referencedColumnName = "id", nullable = false)
  private Airport from;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_aeroporto_destino", referencedColumnName = "id", nullable = false)
  private Airport to;

  @Column(name = "data_hora_embarque", nullable = false)
  private LocalDateTime boarding;

  @Convert(converter = StatusConverter.class)
  @Column(name = "status", nullable = false)
  private StatusEnum status;

  @CreationTimestamp
  @Column(name = "data_criacao", columnDefinition = "TIMESTAMP", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "data_atualizacao", columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
  private List<FlightClass> flightClasses;

}
