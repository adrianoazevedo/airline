package br.com.hrzon.airline.domain.enums;

import lombok.Getter;

@Getter
public enum FlightClassEnum {

  EXECUTIVE(1, "EXECUTIVA"),
  ECONOMIC(2, "ECONÔMICA");

  private final int id;
  private final String value;

  FlightClassEnum(int id, String value) {
    this.id = id;
    this.value = value;
  }

}
