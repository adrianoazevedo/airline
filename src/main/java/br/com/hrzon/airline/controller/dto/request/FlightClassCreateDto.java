package br.com.hrzon.airline.controller.dto.request;

import br.com.hrzon.airline.domain.enums.FlightClassEnum;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FlightClassCreateDto {

  private FlightClassEnum type;

  private int totalSeat;

  private BigDecimal price;

}
