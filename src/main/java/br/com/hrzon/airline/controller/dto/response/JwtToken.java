package br.com.hrzon.airline.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken {

  private String login;

  private String token;

}
