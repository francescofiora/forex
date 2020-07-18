package it.francescofiora.forex.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ExchangeRateType
 */
@Validated
public class ExchangeRateType   {

  @Schema(description = "Unique identifier", example = "EUR-USD-2018-11-17T161203625Z", required = true)
  @JsonProperty("id")
  private String id = null;

  @Schema(example = "/exchangerates/EUR-USD-2018-11-17T161203625Z", required = true)
  @JsonProperty("uri")
  private String uri = null;

  @Schema(example = "EUR", required = true)
  @JsonProperty("valutaFrom")
  private String valutaFrom = null;

  @Schema(example = "USD", required = true)
  @JsonProperty("valutaTo")
  private String valutaTo = null;

  @Schema(example = "1.14193", required = true)
  @JsonProperty("exchangeRate")
  private Double exchangeRate = null;

  @Schema(example = "2018-11-17T16:12:03625Z", required = true)
  @JsonProperty("exchangeData")
  private String exchangeData = null;

  public ExchangeRateType id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @NotNull
  @Valid
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ExchangeRateType uri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * Get uri
   * @return uri
  **/
  @NotNull
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public ExchangeRateType valutaFrom(String valutaFrom) {
    this.valutaFrom = valutaFrom;
    return this;
  }

  /**
   * Get valutaFrom
   * @return valutaFrom
  **/
  @NotNull
  public String getValutaFrom() {
    return valutaFrom;
  }

  public void setValutaFrom(String valutaFrom) {
    this.valutaFrom = valutaFrom;
  }

  public ExchangeRateType valutaTo(String valutaTo) {
    this.valutaTo = valutaTo;
    return this;
  }

  /**
   * Get valutaTo
   * @return valutaTo
  **/
  @NotNull
  public String getValutaTo() {
    return valutaTo;
  }

  public void setValutaTo(String valutaTo) {
    this.valutaTo = valutaTo;
  }

  public ExchangeRateType exchangeRate(Double exchangeRate) {
    this.exchangeRate = exchangeRate;
    return this;
  }

  /**
   * Get exchangeRate
   * @return exchangeRate
  **/
  @NotNull
  public Double getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(Double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public ExchangeRateType exchangeData(String exchangeData) {
    this.exchangeData = exchangeData;
    return this;
  }

  /**
   * Get exchangeData
   * @return exchangeData
  **/
  @NotNull
  @Valid
  public String getExchangeData() {
    return exchangeData;
  }

  public void setExchangeData(String exchangeData) {
    this.exchangeData = exchangeData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeRateType exchangeRateType = (ExchangeRateType) o;
    return Objects.equals(this.id, exchangeRateType.id) &&
        Objects.equals(this.uri, exchangeRateType.uri) &&
        Objects.equals(this.valutaFrom, exchangeRateType.valutaFrom) &&
        Objects.equals(this.valutaTo, exchangeRateType.valutaTo) &&
        Objects.equals(this.exchangeRate, exchangeRateType.exchangeRate) &&
        Objects.equals(this.exchangeData, exchangeRateType.exchangeData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uri, valutaFrom, valutaTo, exchangeRate, exchangeData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeRateType {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    valutaFrom: ").append(toIndentedString(valutaFrom)).append("\n");
    sb.append("    valutaTo: ").append(toIndentedString(valutaTo)).append("\n");
    sb.append("    exchangeRate: ").append(toIndentedString(exchangeRate)).append("\n");
    sb.append("    exchangeData: ").append(toIndentedString(exchangeData)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
