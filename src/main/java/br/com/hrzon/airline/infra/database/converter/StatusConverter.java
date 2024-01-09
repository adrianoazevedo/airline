package br.com.hrzon.airline.infra.database.converter;

import br.com.hrzon.airline.domain.enums.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, Integer> {

  @Override
  public Integer convertToDatabaseColumn(StatusEnum statusEnum) {
    return Objects.isNull(statusEnum) ? null : statusEnum.getId();
  }

  @Override
  public StatusEnum convertToEntityAttribute(Integer id) {
    return Objects.isNull(id) ? null : Stream.of(StatusEnum.values()).filter(i -> i.getId() == id).findFirst().orElse(null);
  }

}
