package br.com.hrzon.airline.controller;

import br.com.hrzon.airline.controller.dto.request.AirportCreateDto;
import br.com.hrzon.airline.controller.dto.response.AirportDto;
import br.com.hrzon.airline.domain.service.AirportService;
import br.com.hrzon.airline.infra.database.entity.Airport;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Aeroporto")
@RequestMapping("/airports")
@SecurityRequirement(name = "bearer-key")
public class AirportController {

  private final AirportService service;

  public AirportController(AirportService service) {
    this.service = service;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AirportDto>> findAll() {
    List<AirportDto> airports = service.findAll().stream().map(AirportDto::toResponse).toList();
    return new ResponseEntity<>(airports, HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AirportDto> create(@RequestBody AirportCreateDto dto) {
    Airport entity = service.create(dto);
    return new ResponseEntity<>(AirportDto.toResponse(entity), HttpStatus.CREATED);
  }

}
