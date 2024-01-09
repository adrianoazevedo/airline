package br.com.hrzon.airline.infra.database.repository;

import br.com.hrzon.airline.infra.database.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {

  Airport findByIata(String iata);

}
