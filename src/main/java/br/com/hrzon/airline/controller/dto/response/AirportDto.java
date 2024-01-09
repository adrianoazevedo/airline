package br.com.hrzon.airline.controller.dto.response;

import br.com.hrzon.airline.infra.database.entity.Airport;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Tag(name = "Aeroporto DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto {

  private String name;

  private String iata;

  private String city;

  public static AirportDto toResponse(Airport entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    AirportDto response = new AirportDto();
    response.setName(entity.getName());
    response.setIata(entity.getIata());
    response.setCity(entity.getCity().getName() + "/" + entity.getCity().getUf());

    return response;
  }

}
