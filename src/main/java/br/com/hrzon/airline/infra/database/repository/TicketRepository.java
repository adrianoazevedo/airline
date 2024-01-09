package br.com.hrzon.airline.infra.database.repository;

import br.com.hrzon.airline.infra.database.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository  extends JpaRepository<Ticket, UUID> {

    List<Ticket> findByPurchaseNumber(String number);

    Ticket findByNumber(String ticketNumber);

}
