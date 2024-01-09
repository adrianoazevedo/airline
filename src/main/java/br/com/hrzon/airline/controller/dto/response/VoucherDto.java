package br.com.hrzon.airline.controller.dto.response;

import br.com.hrzon.airline.infra.database.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDto {

    private String ticket;

    private String flight;

    private String from;

    private String to;

    private String name;

    private String cpf;

    private Boolean hasDispatched;

    public static VoucherDto toResponse(Ticket entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        VoucherDto response = new VoucherDto();
        response.setTicket(entity.getNumber());
        response.setFlight(entity.getFlightClass().getFlight().getNumber());
        response.setFrom(entity.getFlightClass().getFlight().getFrom().getName() + "/" + entity.getFlightClass().getFlight().getFrom().getIata());
        response.setTo(entity.getFlightClass().getFlight().getTo().getName() + "/" + entity.getFlightClass().getFlight().getTo().getIata());
        response.setName(entity.getPassenger().getName());
        response.setCpf(entity.getPassenger().getCpf());
        response.setHasDispatched(entity.getBagDispatched());

        return response;
    }

}
