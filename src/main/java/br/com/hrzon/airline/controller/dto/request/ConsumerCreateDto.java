package br.com.hrzon.airline.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ConsumerCreateDto {

  private String name;

  private String cpf;

  private String email;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;

}
