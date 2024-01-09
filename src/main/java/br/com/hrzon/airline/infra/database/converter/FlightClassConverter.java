package br.com.hrzon.airline.infra.database.converter;

import br.com.hrzon.airline.domain.enums.FlightClassEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class FlightClassConverter implements AttributeConverter<FlightClassEnum, Integer> {

  @Override
  public Integer convertToDatabaseColumn(FlightClassEnum flightClassEnum) {
    return Objects.isNull(flightClassEnum) ? null : flightClassEnum.getId();
  }

  @Override
  public FlightClassEnum convertToEntityAttribute(Integer id) {
    return Objects.isNull(id) ? null : Stream.of(FlightClassEnum.values()).filter(i -> i.getId() == id).findFirst().orElse(null);
  }

}
