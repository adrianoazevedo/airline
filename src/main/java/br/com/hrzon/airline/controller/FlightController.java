package br.com.hrzon.airline.controller;

import br.com.hrzon.airline.controller.dto.request.FlightCreateDto;
import br.com.hrzon.airline.controller.dto.request.FlightUpdateDto;
import br.com.hrzon.airline.controller.dto.response.FlightDto;
import br.com.hrzon.airline.controller.dto.response.FlightPassengerDto;
import br.com.hrzon.airline.domain.service.FlightService;
import br.com.hrzon.airline.infra.database.entity.Flight;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Voos")
@RequestMapping("/flights")
public class FlightController {

  private FlightService service;

  public FlightController(FlightService service) {
    this.service = service;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<FlightDto>> findAll(
          @RequestParam String iataFrom,
          @RequestParam String iataTo,
          @RequestParam String date
  ) {
    List<Flight> entities = service.findAll(iataFrom, iataTo, date);
    List<FlightDto> response = entities.stream().map(FlightDto::toResponse).toList();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/{number}")
  public ResponseEntity<FlightDto> findByNumber(@PathVariable String number) {
    Flight entity = service.findByNumber(number);
    return new ResponseEntity<>(FlightDto.toResponse(entity), HttpStatus.OK);
  }

  @GetMapping(value = "/{number}/passengers")
  public ResponseEntity<FlightPassengerDto> findPassengers(@PathVariable String number) {
    Flight entity = service.findByNumber(number);
    return new ResponseEntity<>(FlightPassengerDto.toResponse(entity), HttpStatus.OK);
  }

  @SecurityRequirement(name = "bearer-key")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FlightDto> create(@RequestBody FlightCreateDto dto) {
    Flight entity = service.create(dto);
    return new ResponseEntity<>(FlightDto.toResponse(entity), HttpStatus.CREATED);
  }

  @SecurityRequirement(name = "bearer-key")
  @PutMapping(value = "/{number}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FlightDto> update(@PathVariable String number, @RequestBody FlightUpdateDto dto) {
    Flight entity = service.update(number, dto);
    return new ResponseEntity<>(FlightDto.toResponse(entity), HttpStatus.CREATED);
  }

  @SecurityRequirement(name = "bearer-key")
  @PatchMapping(value = "/{number}/cancel")
  public ResponseEntity cancel(@PathVariable String number) {
    service.cancel(number);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
