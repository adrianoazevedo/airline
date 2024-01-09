package br.com.hrzon.airline.controller.dto.response;

import br.com.hrzon.airline.infra.database.entity.FlightClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightClassDto {

  private String type;

  private int totalSeat;

  private int totalAvailable;

  private BigDecimal price;

  public static FlightClassDto toResponse(FlightClass entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    FlightClassDto response = new FlightClassDto();
    response.setType(entity.getClassType().name());
    response.setTotalSeat(entity.getTotalSeat());
    response.setTotalAvailable(entity.getTotalAvailable());
    response.setPrice(entity.getPrice());

    return response;
  }

  public static List<FlightClassDto> toResponse(List<FlightClass> entities) {
    return Objects.isNull(entities) ? null : entities.stream().map(FlightClassDto::toResponse).toList();
  }

}
