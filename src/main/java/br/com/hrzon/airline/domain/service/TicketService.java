package br.com.hrzon.airline.domain.service;

import br.com.hrzon.airline.controller.dto.request.PassengerCreateDto;
import br.com.hrzon.airline.controller.dto.request.TicketCreateDto;
import br.com.hrzon.airline.domain.enums.StatusEnum;
import br.com.hrzon.airline.domain.exception.BusinessException;
import br.com.hrzon.airline.infra.database.entity.*;
import br.com.hrzon.airline.infra.database.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class TicketService {

  private final TicketRepository repository;
  private final FlightService flightService;
  private final FlightClassService flightClassService;
  private final ConsumerService consumerService;
  private final PassengerService passengerService;

  public TicketService(TicketRepository repository, FlightService flightService, FlightClassService flightClassService, ConsumerService consumerService, PassengerService passengerService) {
    this.repository = repository;
    this.flightService = flightService;
    this.flightClassService = flightClassService;
    this.consumerService = consumerService;
    this.passengerService = passengerService;
  }

  public List<Ticket> findByTicketNumber(String number) {
    List<Ticket> entities = repository.findByPurchaseNumber(number);

    if (entities.isEmpty()) {
      throw new BusinessException(404, "Compra não encontrada");
    }

    return entities;
  }

  @Transactional
  public List<Ticket> create(TicketCreateDto dto) {
    verifyRequest(dto);

    Flight flight = flightService.findByNumber(dto.getFlight());
    FlightClass flightClass = flight.getFlightClasses()
            .stream()
            .filter(i -> i.getClassType().name().equals(dto.getClasse()))
            .findAny()
            .orElse(null);

    if (Objects.isNull(flightClass)) {
      throw new BusinessException(404, "Classe escolhida não foi encontrada para o voo informado");
    }

    if (dto.getQtdTickets() > flightClass.getTotalAvailable()) {
      throw new BusinessException(400, "Quantidade de assentos indisponível para a classe escolhida");
    }

    Consumer consumer = consumerService.create(dto.getConsumer());

    List<Passenger> passengerList = new ArrayList<>();
    for (PassengerCreateDto passengerCreateDto : dto.getPassengers()) {
      Passenger passenger = passengerService.create(passengerCreateDto);
      passengerList.add(passenger);
    }

    List<Ticket> ticketList = new ArrayList<>();
    String purchaseNumber = getRandomNumber();
    for (Passenger passenger : passengerList) {
      Ticket entity = new Ticket();
      entity.setNumber(getRandomNumber());
      entity.setPurchaseNumber(purchaseNumber);
      entity.setConsumer(consumer);
      entity.setPassenger(passenger);
      entity.setFlightClass(flightClass);
      entity.setTicketValue(flightClass.getPrice());
      entity.setStatus(StatusEnum.ACTIVE);

      PassengerCreateDto passengerDto = dto.getPassengers().stream().filter(i -> i.getCpf().equals(passenger.getCpf())).findFirst().orElse(null);
      entity.setBagDispatched(passengerDto.getHasDispatched());

      if (passengerDto.getHasDispatched()) {
        entity.setNumBaggage(getRandomNumber());
        entity.setTotalValue(flightClass.getPrice().multiply(new BigDecimal(1.1)));
      } else {
        entity.setTotalValue(flightClass.getPrice());
      }

      repository.save(entity);
      ticketList.add(entity);
    }

    flightClassService.decrement(flightClass, dto.getQtdTickets());

    return ticketList;
  }

  private void verifyRequest(TicketCreateDto dto) {
    if (dto.getQtdTickets() != dto.getPassengers().size()) {
      throw new BusinessException(400, "A quantidade de passageiros é diferente da quantidade de assentos solicitados");
    }
  }

  private String getRandomNumber() {
    Random random = new Random();
    return random
            .ints(97, 122)
            .limit(4)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString().toUpperCase();
  }

  @Transactional
  public void cancel(String number) {
    List<Ticket> entities = repository.findByPurchaseNumber(number);

    if (entities.isEmpty()) {
      throw new BusinessException(404, "Número da compra não encontrado");
    }

    for (Ticket ticket : entities) {
      ticket.setStatus(StatusEnum.CANCELED);
    }
    repository.saveAll(entities);

    flightClassService.increase(entities.get(0).getFlightClass(), entities.size());
  }

  public Ticket findVoucherByTicketNumber(String ticketNumber) {
    Ticket entity = repository.findByNumber(ticketNumber);

    if (Objects.isNull(entity)) {
      throw new BusinessException(404, "Ticket não encontrado");
    }

    verifyTimeExpired(entity.getFlightClass().getFlight().getBoarding());

    return entity;
  }

  public Ticket findTagByTicketNumber(String ticketNumber) {
    Ticket entity = findVoucherByTicketNumber(ticketNumber);

    if (!entity.getBagDispatched()) {
      throw new BusinessException(404, "Não existe bagagem a ser despachada");
    }

    verifyTimeExpired(entity.getFlightClass().getFlight().getBoarding());

    return entity;
  }

  public void verifyTimeExpired(LocalDateTime boarding) {
    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(boarding.minusHours(5L))) {
      throw new BusinessException(412, "Tempo expirado para emissão do documento");
    }
  }

}
