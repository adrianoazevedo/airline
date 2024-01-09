package br.com.hrzon.airline.controller.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class TicketCreateDto {

  private String flight;

  private String classe;

  private int qtdTickets;

  private ConsumerCreateDto consumer;

  private List<PassengerCreateDto> passengers;

}
