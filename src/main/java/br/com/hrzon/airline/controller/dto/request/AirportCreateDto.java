package br.com.hrzon.airline.controller.dto.request;

import lombok.Getter;

@Getter
public class AirportCreateDto {

  private String name;

  private String iata;

  private String idCity;

}
