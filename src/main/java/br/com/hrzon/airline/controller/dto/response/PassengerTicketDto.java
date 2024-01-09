package br.com.hrzon.airline.controller.dto.response;

import br.com.hrzon.airline.infra.database.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerTicketDto {

  private String name;

  private String cpf;

  private LocalDate birthday;

  private String purchaseNumber;

  private String ticket;

  private BigDecimal price;

  private Boolean hasDispatched;

  private String status;

  public static PassengerTicketDto toResponse(Ticket entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    PassengerTicketDto response = new PassengerTicketDto();
    response.setName(entity.getPassenger().getName());
    response.setCpf(entity.getPassenger().getCpf());
    response.setBirthday(entity.getPassenger().getBirthday());
    response.setPurchaseNumber(entity.getPurchaseNumber());
    response.setTicket(entity.getNumber());
    response.setPrice(entity.getTotalValue().setScale(2, RoundingMode.DOWN));
    response.setHasDispatched(entity.getBagDispatched());
    response.setStatus(entity.getStatus().getValue());

    return response;
  }

  public static List<PassengerTicketDto> toResponse(List<Ticket> entities) {
    return Objects.isNull(entities) ? null : entities.stream().map(PassengerTicketDto::toResponse).toList();
  }
}
