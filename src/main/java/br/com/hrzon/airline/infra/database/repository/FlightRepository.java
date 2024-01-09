package br.com.hrzon.airline.infra.database.repository;

import br.com.hrzon.airline.infra.database.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {

  Flight findByNumber(String number);

  List<Flight> findAllByFromIataAndToIataAndBoardingBetween(String iataFrom, String iataTo, LocalDateTime date, LocalDateTime boardingEnd);

}
