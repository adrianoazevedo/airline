package br.com.hrzon.airline.controller.dto.response;

import br.com.hrzon.airline.infra.database.entity.Consumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDto {

  private String name;

  private String cpf;

  private String email;

  private LocalDate birthday;

  public static ConsumerDto toResponse(Consumer entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    ConsumerDto response = new ConsumerDto();
    response.setName(entity.getName());
    response.setCpf(entity.getCpf());
    response.setEmail(entity.getEmail());
    response.setBirthday(entity.getBirthday());

    return response;
  }

}
