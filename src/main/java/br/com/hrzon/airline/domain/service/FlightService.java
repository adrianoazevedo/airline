package br.com.hrzon.airline.domain.service;

import br.com.hrzon.airline.controller.dto.request.FlightClassCreateDto;
import br.com.hrzon.airline.controller.dto.request.FlightCreateDto;
import br.com.hrzon.airline.controller.dto.request.FlightUpdateDto;
import br.com.hrzon.airline.domain.enums.StatusEnum;
import br.com.hrzon.airline.domain.exception.BusinessException;
import br.com.hrzon.airline.infra.database.entity.Airport;
import br.com.hrzon.airline.infra.database.entity.Flight;
import br.com.hrzon.airline.infra.database.entity.FlightClass;
import br.com.hrzon.airline.infra.database.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FlightService {

  private final FlightRepository repository;
  private final AirportService airportService;
  private final FlightClassService flightClassService;

  public FlightService(FlightRepository repository, AirportService airportService, FlightClassService flightClassService) {
    this.repository = repository;
    this.airportService = airportService;
    this.flightClassService = flightClassService;
  }

  public List<Flight> findAll(String iataFrom, String iataTo, String date) {
    DateTimeFormatter dtfIni = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dtfEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime boardingIni = LocalDateTime.parse(date.concat(" 00:00:00"), dtfIni);
    LocalDateTime boardingEnd = LocalDateTime.parse(date.concat(" 23:59:59"), dtfEnd);

    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(boardingEnd)) {
      throw new BusinessException(400, "Não é possível listar voos anteriores");
    }

    List<Flight> entities = repository.findAllByFromIataAndToIataAndBoardingBetween(iataFrom, iataTo, boardingIni, boardingEnd);

    List<Flight> flights = new ArrayList<>();
    for (Flight flight : entities) {
      List<FlightClass> flightClasses = flight.getFlightClasses().stream().filter(i -> i.getTotalAvailable() > 0).toList();
      if (!flightClasses.isEmpty()) {
        flight.setFlightClasses(flightClasses);
        flights.add(flight);
      }
    }

    return flights;
  }

  public Flight findById(UUID id) {
    Flight entity = repository.findById(id).orElse(null);

    if (Objects.isNull(entity)) {
      throw new BusinessException(404, "Voo não encontrado");
    }

    return entity;
  }

  public Flight findByNumber(String number) {
    Flight entity = repository.findByNumber(number);

    if (Objects.isNull(entity)) {
      throw new BusinessException(404, "Voo não encontrado");
    }

    return entity;
  }

  @Transactional
  public Flight create(FlightCreateDto dto) {
    verifyCreateRequest(dto);

    Airport from = airportService.findByIata(dto.getIataAirportOrigin());
    Airport to = airportService.findByIata(dto.getIataAirportDestiny());

    if (from.getCity().getId().equals(to.getCity().getId())) {
      throw new BusinessException(400, "Não é permitido voos para aeroportos da mesma cidade");
    }

    Flight entity = new Flight();
    entity.setNumber(getRandomNumber());
    entity.setBoarding(dto.getBoarding());
    entity.setStatus(StatusEnum.ACTIVE);
    entity.setFrom(from);
    entity.setTo(to);

    repository.save(entity);

    List<FlightClass> flightClassList = flightClassService.createByList(entity, dto.getClasses());
    entity.setFlightClasses(flightClassList);

    return entity;
  }

  @Transactional
  public Flight update(String number, FlightUpdateDto dto) {
    Flight entity = findByNumber(number);

    if (dto.getIataAirportOrigin().equals(dto.getIataAirportDestiny())) {
      throw new BusinessException(400, "Aeroporto de origem não pode ser igual ao de destino");
    }

    Airport from = airportService.findByIata(dto.getIataAirportOrigin());
    Airport to = airportService.findByIata(dto.getIataAirportDestiny());

    if (from.getCity().getId().equals(to.getCity().getId())) {
      throw new BusinessException(400, "Não é permitido voos para aeroportos da mesma cidade");
    }

    entity.setBoarding(dto.getBoarding());
    entity.setFrom(from);
    entity.setTo(to);

    repository.save(entity);

    return entity;
  }

  @Transactional
  public void cancel(String number) {
    Flight entity = findByNumber(number);
    entity.setStatus(StatusEnum.CANCELED);
    repository.save(entity);
  }

  private void verifyCreateRequest(FlightCreateDto dto) {
    if (dto.getIataAirportOrigin().equals(dto.getIataAirportDestiny())) {
      throw new BusinessException(400, "Aeroporto de origem não pode ser igual ao de destino");
    }

    if (dto.getClasses().isEmpty()) {
      throw new BusinessException(400, "É necessário informar pelo menos uma classe");
    }

    HashSet<String> duplicates = new HashSet<>();
    for (FlightClassCreateDto flightClassCreateDto : dto.getClasses()) {
      duplicates.add(flightClassCreateDto.getType().name());
    }
    if (duplicates.size() < dto.getClasses().size()) {
      throw new BusinessException(400, "Não é permitido cadastrar classes iguais");
    }
  }

  private String getRandomNumber() {
    Random random = new Random();
    return random
            .ints(97, 122)
            .limit(6)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString().toUpperCase();
  }

}
