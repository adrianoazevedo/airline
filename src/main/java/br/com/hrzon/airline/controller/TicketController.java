package br.com.hrzon.airline.controller;

import br.com.hrzon.airline.controller.dto.request.TicketCreateDto;
import br.com.hrzon.airline.controller.dto.response.TicketDto;
import br.com.hrzon.airline.domain.service.TicketService;
import br.com.hrzon.airline.infra.database.entity.Ticket;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Passagens")
@RequestMapping("/tickets")
public class TicketController {

  private final TicketService service;

  public TicketController(TicketService service) {
    this.service = service;
  }

  @GetMapping(value = "/{number}")
  public ResponseEntity<TicketDto> findByNumber(@PathVariable String number) {
    List<Ticket> tickets = service.findByTicketNumber(number);
    return new ResponseEntity<>(TicketDto.toResponse(tickets), HttpStatus.CREATED);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TicketDto> create(@RequestBody TicketCreateDto dto) {
    List<Ticket> tickets = service.create(dto);
    return new ResponseEntity<>(TicketDto.toResponse(tickets), HttpStatus.CREATED);
  }

  @SecurityRequirement(name = "bearer-key")
  @PatchMapping(value = "/{number}/cancel")
  public ResponseEntity cancel(@PathVariable String number) {
    service.cancel(number);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
