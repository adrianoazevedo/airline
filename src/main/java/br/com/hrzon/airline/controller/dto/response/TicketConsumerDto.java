package br.com.hrzon.airline.controller.dto.response;

import br.com.hrzon.airline.infra.database.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketConsumerDto {

    private ConsumerDto consumer;

    private List<PassengerTicketDto> tickets;

    public static TicketConsumerDto toResponse(List<Ticket> entities) {
        if (Objects.isNull(entities)) {
            return null;
        }

        TicketConsumerDto response = new TicketConsumerDto();
        response.setConsumer(ConsumerDto.toResponse(entities.get(0).getConsumer()));
        response.setTickets(PassengerTicketDto.toResponse(entities));

        return response;
    }

}
