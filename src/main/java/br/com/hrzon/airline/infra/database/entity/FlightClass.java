package br.com.hrzon.airline.infra.database.entity;

import br.com.hrzon.airline.domain.enums.FlightClassEnum;
import br.com.hrzon.airline.infra.database.converter.FlightClassConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "voo_classe")
@Data
public class FlightClass {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_voo")
  private Flight flight;

  @Convert(converter = FlightClassConverter.class)
  @Column(name = "classe", nullable = false)
  private FlightClassEnum classType;

  @Column(name = "qtd_assentos", nullable = false)
  private int totalSeat;

  @Column(name = "qtd_livres", nullable = false)
  private int totalAvailable;

  @Column(name = "valor", nullable = false)
  private BigDecimal price;

  @CreationTimestamp
  @Column(name = "data_criacao", columnDefinition = "TIMESTAMP", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "data_atualizacao", columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "flightClass", fetch = FetchType.LAZY)
  private List<Ticket> ticket;

}
