package br.com.hrzon.airline.controller;

import br.com.hrzon.airline.config.auth.JwtUtil;
import br.com.hrzon.airline.controller.dto.request.Login;
import br.com.hrzon.airline.controller.dto.response.ErrorResponse;
import br.com.hrzon.airline.controller.dto.response.JwtToken;
import br.com.hrzon.airline.domain.exception.BusinessException;
import br.com.hrzon.airline.infra.database.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login")
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;

  private JwtUtil jwtUtil;

  public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity login(@RequestBody Login dto) {
    try {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
      String login = authentication.getName();
      User user = new User(login, "");
      String token = jwtUtil.createToken(user);

      return new ResponseEntity<>(new JwtToken(login, token), HttpStatus.OK);
    } catch (BusinessException ex) {
      ErrorResponse errorResponse = new ErrorResponse(ex.getStatus(), ex.getMessage());
      return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus()));
    } catch (BadCredentialsException ex) {
      ErrorResponse errorResponse = new ErrorResponse(400, "Credenciais inválidas");
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      ErrorResponse errorResponse = new ErrorResponse(400, ex.getMessage());
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
  }

}
