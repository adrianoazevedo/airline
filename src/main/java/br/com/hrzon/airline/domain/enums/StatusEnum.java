package br.com.hrzon.airline.domain.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

  ACTIVE(1, "ATIVO"),
  CANCELED(2, "CANCELADO");

  private final int id;
  private final String value;

  StatusEnum(int id, String value) {
    this.id = id;
    this.value = value;
  }

}
