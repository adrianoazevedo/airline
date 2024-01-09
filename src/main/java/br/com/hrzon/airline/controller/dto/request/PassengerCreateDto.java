package br.com.hrzon.airline.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PassengerCreateDto {

  private String name;

  private String cpf;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;

  private Boolean hasDispatched;

}
