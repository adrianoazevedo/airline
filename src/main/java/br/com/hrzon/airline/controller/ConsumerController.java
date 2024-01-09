package br.com.hrzon.airline.controller;

import br.com.hrzon.airline.controller.dto.response.TicketConsumerDto;
import br.com.hrzon.airline.domain.service.ConsumerService;
import br.com.hrzon.airline.infra.database.entity.Ticket;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Compradores")
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerService service;

    public ConsumerController(ConsumerService service) {
        this.service = service;
    }

    @GetMapping(value = "/{cpf}/tickets")
    public ResponseEntity<TicketConsumerDto> findTicketByCpf(@PathVariable String cpf) {
        List<Ticket> entities = service.findTicketsByCpf(cpf);
        return new ResponseEntity<>(TicketConsumerDto.toResponse(entities), HttpStatus.OK);
    }

}
