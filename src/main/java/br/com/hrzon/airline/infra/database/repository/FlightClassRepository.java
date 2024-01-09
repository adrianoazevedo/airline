package br.com.hrzon.airline.infra.database.repository;

import br.com.hrzon.airline.infra.database.entity.FlightClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlightClassRepository extends JpaRepository<FlightClass, UUID> {
}
