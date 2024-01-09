package br.com.hrzon.airline.controller.dto.response;

import br.com.hrzon.airline.infra.database.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

  private String number;

  private LocalDateTime boarding;

  private AirportDto from;

  private AirportDto to;

  private String status;

  private List<FlightClassDto> classes;

  public static FlightDto toResponse(Flight entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    FlightDto response = new FlightDto();
    response.setNumber(entity.getNumber());
    response.setBoarding(entity.getBoarding());
    response.setFrom(AirportDto.toResponse(entity.getFrom()));
    response.setTo(AirportDto.toResponse(entity.getTo()));
    response.setStatus(entity.getStatus().getValue());
    response.setClasses(FlightClassDto.toResponse(entity.getFlightClasses()));

    return response;
  }

}
