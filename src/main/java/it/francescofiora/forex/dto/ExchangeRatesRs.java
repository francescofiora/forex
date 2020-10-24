package it.francescofiora.forex.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * ExchangeRatesRs
 */
@Validated
public class ExchangeRatesRs {

  @Schema(example = "1")
  @JsonProperty("page")
  private Integer page = null;

  @Schema(example = "1")
  @JsonProperty("item")
  private Integer item = null;

  @Schema(example = "1")
  @JsonProperty("totalItem")
  private Integer totalItem = null;

  @JsonProperty("exchangeRates")
  @Valid
  private List<ExchangeRateType> exchangeRates = null;

  public ExchangeRatesRs page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   * 
   * @return page
   **/
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public ExchangeRatesRs item(Integer item) {
    this.item = item;
    return this;
  }

  /**
   * Get item
   * 
   * @return item
   **/
  public Integer getItem() {
    return item;
  }

  public void setItem(Integer item) {
    this.item = item;
  }

  public ExchangeRatesRs totalItem(Integer totalItem) {
    this.totalItem = totalItem;
    return this;
  }

  /**
   * Get totalItem
   * 
   * @return totalItem
   **/
  public Integer getTotalItem() {
    return totalItem;
  }

  public void setTotalItem(Integer totalItem) {
    this.totalItem = totalItem;
  }

  public ExchangeRatesRs exchangeRates(List<ExchangeRateType> exchangeRates) {
    this.exchangeRates = exchangeRates;
    return this;
  }

  public ExchangeRatesRs addExchangeRatesItem(ExchangeRateType exchangeRatesItem) {
    if (this.exchangeRates == null) {
      this.exchangeRates = new ArrayList<>();
    }
    this.exchangeRates.add(exchangeRatesItem);
    return this;
  }

  /**
   * Get exchangeRates
   * 
   * @return exchangeRates
   **/
  @Valid
  public List<ExchangeRateType> getExchangeRates() {
    return exchangeRates;
  }

  public void setExchangeRates(List<ExchangeRateType> exchangeRates) {
    this.exchangeRates = exchangeRates;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeRatesRs exchangeRatesRs = (ExchangeRatesRs) o;
    return Objects.equals(this.page, exchangeRatesRs.page)
        && Objects.equals(this.item, exchangeRatesRs.item)
        && Objects.equals(this.totalItem, exchangeRatesRs.totalItem)
        && Objects.equals(this.exchangeRates, exchangeRatesRs.exchangeRates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, item, totalItem, exchangeRates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeRatesRs {\n");

    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    item: ").append(toIndentedString(item)).append("\n");
    sb.append("    totalItem: ").append(toIndentedString(totalItem)).append("\n");
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

