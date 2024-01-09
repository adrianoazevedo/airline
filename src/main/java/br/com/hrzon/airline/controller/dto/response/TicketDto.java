package br.com.hrzon.airline.controller.dto.response;


import br.com.hrzon.airline.infra.database.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

  private String flight;

  private String classe;

  private String purchaseNumber;

  private String status;

  private ConsumerDto consumer;

  private List<PassengerTicketDto> passengers;

  public static TicketDto toResponse(Ticket entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    TicketDto response = new TicketDto();
    response.setFlight(entity.getFlightClass().getFlight().getNumber());
    response.setClasse(entity.getFlightClass().getClassType().name());
    response.setPurchaseNumber(entity.getPurchaseNumber());
    response.setStatus(entity.getStatus().getValue());
    response.setConsumer(ConsumerDto.toResponse(entity.getConsumer()));
    response.setPassengers(new ArrayList<>());
    response.getPassengers().add(PassengerTicketDto.toResponse(entity));

    return response;
  }

  public static TicketDto toResponse(List<Ticket> entities) {
    if (Objects.isNull(entities)) {
      return null;
    }

    TicketDto response = new TicketDto();
    response.setFlight(entities.get(0).getFlightClass().getFlight().getNumber());
    response.setClasse(entities.get(0).getFlightClass().getClassType().name());
    response.setPurchaseNumber(entities.get(0).getPurchaseNumber());
    response.setStatus(entities.get(0).getStatus().getValue());
    response.setConsumer(ConsumerDto.toResponse(entities.get(0).getConsumer()));
    response.setPassengers(PassengerTicketDto.toResponse(entities));

    return response;
  }

}
