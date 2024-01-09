package br.com.hrzon.airline.domain.service;

import br.com.hrzon.airline.controller.dto.request.AirportCreateDto;
import br.com.hrzon.airline.domain.exception.BusinessException;
import br.com.hrzon.airline.infra.database.entity.Airport;
import br.com.hrzon.airline.infra.database.entity.City;
import br.com.hrzon.airline.infra.database.repository.AirportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AirportService {

  private final AirportRepository repository;
  private final CityService cityService;

  public AirportService(AirportRepository repository, CityService cityService) {
    this.repository = repository;
    this.cityService = cityService;
  }

  public List<Airport> findAll() {
    return repository.findAll();
  }

  public Airport findById(UUID id) {
    Airport airport = repository.findById(id).orElse(null);

    if (Objects.isNull(airport)) {
      throw new BusinessException(404, "Aeroporto não encontrado");
    }

    return airport;
  }

  public Airport findByIata(String iata) {
    Airport airport = repository.findByIata(iata);

    if (Objects.isNull(airport)) {
      throw new BusinessException(404, "Aeroporto não encontrado");
    }

    return airport;
  }


  @Transactional
  public Airport create(AirportCreateDto dto) {
    Airport airportByIata = repository.findByIata(dto.getIata());
    if (Objects.nonNull(airportByIata)) {
      throw new BusinessException(409, "Aeroporto já existe");
    }

    City city = cityService.findById(UUID.fromString(dto.getIdCity()));

    Airport entity = new Airport();
    entity.setName(dto.getName());
    entity.setIata(dto.getIata());
    entity.setCity(city);

    return repository.save(entity);
  }

}
