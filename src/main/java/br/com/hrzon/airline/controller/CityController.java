package br.com.hrzon.airline.controller;

import br.com.hrzon.airline.domain.service.CityService;
import br.com.hrzon.airline.infra.database.entity.City;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Cidades")
@RequestMapping("/cities")
@SecurityRequirement(name = "bearer-key")
public class CityController {

  private final CityService service;

  public CityController(CityService service) {
    this.service = service;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<City>> findAll() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

}
