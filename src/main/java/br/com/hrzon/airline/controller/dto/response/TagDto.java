package br.com.hrzon.airline.controller.dto.response;

import br.com.hrzon.airline.infra.database.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

    private String ticket;

    private String baggage;

    private String name;

    public static TagDto toResponse(Ticket entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        TagDto response = new TagDto();
        response.setTicket(entity.getNumber());
        response.setBaggage(entity.getNumBaggage());
        response.setName(entity.getPassenger().getName());

        return response;
    }

}
