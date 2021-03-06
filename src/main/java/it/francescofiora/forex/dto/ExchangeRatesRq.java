package it.francescofiora.forex.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ExchangeRatesRq
 */
@Getter
@Setter
public class ExchangeRatesRq {

  @Schema(required = true)
  @JsonProperty("exchangeRates")
  @NotEmpty
  @Valid
  private List<ExchangeRateType> exchangeRates = new ArrayList<>();

  public ExchangeRatesRq exchangeRate(List<ExchangeRateType> exchangeRates) {
    this.exchangeRates = exchangeRates;
    return this;
  }

  public ExchangeRatesRq addExchangeRateItem(ExchangeRateType exchangeRateItem) {
    this.exchangeRates.add(exchangeRateItem);
    return this;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeRatesRq exchangeRatesRq = (ExchangeRatesRq) o;
    return Objects.equals(this.exchangeRates, exchangeRatesRq.exchangeRates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exchangeRates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeRatesRq {\n");

    sb.append("    exchangeRates: ").append(toIndentedString(exchangeRates)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
