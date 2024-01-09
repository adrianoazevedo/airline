package br.com.hrzon.airline.infra.database.entity;

import br.com.hrzon.airline.domain.enums.StatusEnum;
import br.com.hrzon.airline.infra.database.converter.StatusConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "passagem")
@Data
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "numero", length = 50, nullable = false)
  private String number;

  @Column(name = "num_compra", length = 50, nullable = false)
  private String purchaseNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_comprador", referencedColumnName = "id")
  private Consumer consumer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_passageiro", referencedColumnName = "id")
  private Passenger passenger;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_voo_classe", referencedColumnName = "id")
  private FlightClass flightClass;

  @Column(name = "valor_passagem", nullable = false)
  private BigDecimal ticketValue;

  @Column(name = "valor_total", nullable = false)
  private BigDecimal totalValue;

  @Column(name = "num_bagagem")
  private String numBaggage;

  @Column(name = "bag_despachada")
  private Boolean bagDispatched;

  @Convert(converter = StatusConverter.class)
  @Column(name = "status", nullable = false)
  private StatusEnum status;

  @CreationTimestamp
  @Column(name = "data_criacao", columnDefinition = "TIMESTAMP", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "data_atualizacao", columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

}
