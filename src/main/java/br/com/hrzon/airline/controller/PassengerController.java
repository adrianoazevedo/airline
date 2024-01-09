package br.com.hrzon.airline.controller;

import br.com.hrzon.airline.controller.dto.response.TagDto;
import br.com.hrzon.airline.controller.dto.response.VoucherDto;
import br.com.hrzon.airline.domain.service.TicketService;
import br.com.hrzon.airline.infra.database.entity.Ticket;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Passageiros")
@RequestMapping("/passengers")
public class PassengerController {

    private final TicketService service;

    public PassengerController(TicketService service) {
        this.service = service;
    }

    @GetMapping(value = "/{ticketNumber}/voucher", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoucherDto> voucher(@PathVariable String ticketNumber) {
        Ticket entity = service.findVoucherByTicketNumber(ticketNumber);
        return new ResponseEntity<>(VoucherDto.toResponse(entity), HttpStatus.OK);
    }

    @GetMapping(value = "/{ticketNumber}/tag", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TagDto> tag(@PathVariable String ticketNumber) {
        Ticket entity = service.findTagByTicketNumber(ticketNumber);
        return new ResponseEntity<>(TagDto.toResponse(entity), HttpStatus.OK);
    }

}
