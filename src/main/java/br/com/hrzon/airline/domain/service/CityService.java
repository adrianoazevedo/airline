package br.com.hrzon.airline.domain.service;

import br.com.hrzon.airline.domain.exception.BusinessException;
import br.com.hrzon.airline.infra.database.entity.City;
import br.com.hrzon.airline.infra.database.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CityService {

  private final CityRepository repository;

  public CityService(CityRepository repository) {
    this.repository = repository;
  }

  public List<City> findAll() {
    return repository.findAll();
  }

  public City findById(UUID id) {
    City city = repository.findById(id).orElse(null);

    if (Objects.isNull(city)) {
      throw new BusinessException(404, "Cidade não encontrada");
    }

    return city;
  }

}
