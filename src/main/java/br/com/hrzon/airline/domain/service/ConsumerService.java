package br.com.hrzon.airline.domain.service;

import br.com.hrzon.airline.controller.dto.request.ConsumerCreateDto;
import br.com.hrzon.airline.domain.exception.BusinessException;
import br.com.hrzon.airline.infra.database.entity.Consumer;
import br.com.hrzon.airline.infra.database.entity.Ticket;
import br.com.hrzon.airline.infra.database.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ConsumerService {

  private final ConsumerRepository repository;

  public ConsumerService(ConsumerRepository repository) {
    this.repository = repository;
  }

  public List<Ticket> findTicketsByCpf(String cpf) {
    Consumer entity = findByCpf(cpf);
    return entity.getTickets();
  }

  public Consumer findByCpf(String cpf) {
    Consumer entity = repository.findByCpf(cpf);
    if (Objects.isNull(entity)) {
      throw new BusinessException(404, "CPF não encontrado");
    }
    return entity;
  }

  public Consumer create(ConsumerCreateDto dto) {
    Consumer entity = repository.findByCpf(dto.getCpf());

    if (Objects.isNull(entity)) {
      entity = new Consumer();
      entity.setName(dto.getName());
      entity.setCpf(dto.getCpf());
      entity.setEmail(dto.getEmail());
      entity.setBirthday(dto.getBirthday());

      repository.save(entity);
    }

    return entity;
  }

}
