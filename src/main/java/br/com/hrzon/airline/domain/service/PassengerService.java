package br.com.hrzon.airline.domain.service;

import br.com.hrzon.airline.controller.dto.request.PassengerCreateDto;
import br.com.hrzon.airline.infra.database.entity.Passenger;
import br.com.hrzon.airline.infra.database.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PassengerService {

  private final PassengerRepository repository;

  public PassengerService(PassengerRepository repository) {
    this.repository = repository;
  }

  public Passenger findByCpf(String cpf) {
    return repository.findByCpf(cpf);
  }

  public Passenger create(PassengerCreateDto dto) {
    Passenger entity = findByCpf(dto.getCpf());

    if (Objects.isNull(entity)) {
      entity = new Passenger();
      entity.setName(dto.getName());
      entity.setCpf(dto.getCpf());
      entity.setBirthday(dto.getBirthday());

      repository.save(entity);
    }

    return entity;
  }

}
