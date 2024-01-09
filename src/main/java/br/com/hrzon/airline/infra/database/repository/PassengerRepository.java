package br.com.hrzon.airline.infra.database.repository;

import br.com.hrzon.airline.infra.database.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, UUID> {

  Passenger findByCpf(String cpf);

}
