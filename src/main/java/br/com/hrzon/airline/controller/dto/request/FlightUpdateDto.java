package br.com.hrzon.airline.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FlightUpdateDto {

    private String iataAirportOrigin;

    private String iataAirportDestiny;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime boarding;

}
