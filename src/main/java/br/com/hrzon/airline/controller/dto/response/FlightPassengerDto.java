package br.com.hrzon.airline.controller.dto.response;

import br.com.hrzon.airline.domain.enums.StatusEnum;
import br.com.hrzon.airline.infra.database.entity.Flight;
import br.com.hrzon.airline.infra.database.entity.FlightClass;
import br.com.hrzon.airline.infra.database.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightPassengerDto {

    private String number;

    private LocalDateTime boarding;

    private AirportDto from;

    private AirportDto to;

    private String status;

    private List<PassengerTicketDto> passengers;

    public static FlightPassengerDto toResponse(Flight entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        FlightPassengerDto response = new FlightPassengerDto();
        response.setNumber(entity.getNumber());
        response.setBoarding(entity.getBoarding());
        response.setFrom(AirportDto.toResponse(entity.getFrom()));
        response.setTo(AirportDto.toResponse(entity.getTo()));
        response.setStatus(entity.getStatus().getValue());

        List<PassengerTicketDto> passengerTicketDtoList = new ArrayList<>();
        for (FlightClass flightClass : entity.getFlightClasses()) {
            for (Ticket ticket : flightClass.getTicket()) {
                if (ticket.getStatus().equals(StatusEnum.ACTIVE)) {
                    passengerTicketDtoList.add(PassengerTicketDto.toResponse(ticket));
                }
            }
        }
        response.setPassengers(passengerTicketDtoList);

        return response;
    }

}
