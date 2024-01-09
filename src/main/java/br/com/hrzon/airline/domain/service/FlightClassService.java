package br.com.hrzon.airline.domain.service;

import br.com.hrzon.airline.controller.dto.request.FlightClassCreateDto;
import br.com.hrzon.airline.infra.database.entity.Flight;
import br.com.hrzon.airline.infra.database.entity.FlightClass;
import br.com.hrzon.airline.infra.database.repository.FlightClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightClassService {

  private final FlightClassRepository repository;

  public FlightClassService(FlightClassRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public List<FlightClass> createByList(Flight flight, List<FlightClassCreateDto> flightClassCreateDtos) {
    List<FlightClass> flightClassList = new ArrayList<>();
    for (FlightClassCreateDto flightClassDto : flightClassCreateDtos) {
      FlightClass entity = new FlightClass();
      entity.setFlight(flight);
      entity.setClassType(flightClassDto.getType());
      entity.setTotalSeat(flightClassDto.getTotalSeat());
      entity.setTotalAvailable(flightClassDto.getTotalSeat());
      entity.setPrice(flightClassDto.getPrice());

      flightClassList.add(entity);
    }

    repository.saveAll(flightClassList);

    return flightClassList;
  }

  public void increase(FlightClass flightClass, int qtdTickets) {
    int totalAvailable = flightClass.getTotalAvailable();
    flightClass.setTotalAvailable(totalAvailable + qtdTickets);

    repository.save(flightClass);
  }

  public void decrement(FlightClass flightClass, int qtdTickets) {
    int totalAvailable = flightClass.getTotalAvailable();
    flightClass.setTotalAvailable(totalAvailable - qtdTickets);

    repository.save(flightClass);
  }

}
