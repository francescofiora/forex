package it.francescofiora.forex.rest;

import it.francescofiora.forex.manager.ExchangeRateManager;
import it.francescofiora.forex.model.ExchangeRateType;
import it.francescofiora.forex.model.ExchangeRatesRq;
import it.francescofiora.forex.model.ExchangeRatesRs;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Exchange Rate Rest Api.
 * @author francesco.fiora
 */
@Api(value = "exchangerates", description = "the exchangerates API")
@RestController
public class ExchangeRateRest {
  
  @Autowired
  private ExchangeRateManager exchangeRateManager;

  @ApiOperation(value = "adds exchange rates",
      nickname = "addExchangeRates",
      notes = "adds exchange rate to the system", tags={  })
  @ApiResponses(value = { 
      @ApiResponse(code = 201, message = "items created"),
      @ApiResponse(code = 400, message = "invalid input, object invalid"),
      @ApiResponse(code = 409, message = "an existing item already exists") })
  @PostMapping(value = "/exchangerates",
      produces = { "application/json" }, 
      consumes = { "application/json" })
  ResponseEntity<Void> addExchangeRates(
      @ApiParam("adds list of exchange rate")
      @Valid @RequestBody ExchangeRatesRq exchangeRatesRq) {
    exchangeRateManager.addExchangeRates(exchangeRatesRq);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }


  @ApiOperation(value = "searches exchange rate by 'exchangerateId'",
      nickname = "searchExchangeRate",
      notes = "searches exchange rate by 'exchangerateId'",
      response = ExchangeRateType.class, tags={  })
  @ApiResponses(value = { 
      @ApiResponse(code = 200, message = "search results matching criteria", response = ExchangeRateType.class),
      @ApiResponse(code = 400, message = "bad input parameter") })
  @GetMapping(value = "/exchangerates/{exchangerateId}",
      produces = { "application/json" })
  ResponseEntity<?> searchExchangeRate(
      @ApiParam(value = "ID of the exchange rate to get", required=true, example = "EUR-USD-2018-11-17T161203625Z")
        @PathVariable("exchangerateId") String exchangerateId) {
    return new ResponseEntity<ExchangeRateType>(
        exchangeRateManager.searchExchangeRate(exchangerateId), HttpStatus.OK);
  }


  @ApiOperation(value = "searches exchange rates",
      nickname = "searchExchangeRates",
      notes = "By passing in the appropriate options, you can search for available exchange rates in the system ",
      response = ExchangeRatesRs.class, tags={  })
  @ApiResponses(value = { 
      @ApiResponse(code = 200, message = "search results matching criteria", response = ExchangeRatesRs.class),
      @ApiResponse(code = 400, message = "bad input parameter") })
  @GetMapping(value = "/exchangerates",
      produces = { "application/json" })
  ResponseEntity<?> searchExchangeRates(
      @ApiParam("pass an optional search for filter by data and time")
      @Valid @RequestParam(value = "dataFrom", required = false)
      String dataFrom,
      @ApiParam("pass an optional search for filter by data and type")
      @Valid @RequestParam(value = "dataTo", required = false)
      String dataTo,
      @ApiParam(value = "pass an optional search for filter by valutaFrom", example = "EUR")
      @Valid @RequestParam(value = "valutaFrom", required = false)
      String valutaFrom,
      @ApiParam(value = "pass an optional search for filter by valutaTo", example = "USD")
      @Valid @RequestParam(value = "valutaTo", required = false)
      String valutaTo,
      @Min(0) @ApiParam(value = "number of records to skip for pagination", example = "0")
      @Valid @RequestParam(value = "skip", required = false)
      Integer skip,
      @Min(50) @Max(100) @ApiParam(value = "maximum number of records to return", example = "50")
      @Valid @RequestParam(value = "limit", required = false)
      Integer limit) {
    return new ResponseEntity<ExchangeRatesRs>(
        exchangeRateManager.searchExchangeRates(dataFrom, dataTo, valutaFrom, valutaTo, skip, limit),
        HttpStatus.OK);
  }


  @ApiOperation(value = "updates exchange rates", nickname = "updateExchangeRates",
      notes = "updates exchange rates to the system", tags={  })
  @ApiResponses(value = { 
      @ApiResponse(code = 201, message = "items updated"),
      @ApiResponse(code = 400, message = "invalid input, object invalid") })
  @PutMapping(value = "/exchangerates",
      produces = { "application/json" }, 
      consumes = { "application/json" })
  ResponseEntity<Void> updateExchangeRates(
      @ApiParam(value = "updates list of exchange rate"  ) @Valid @RequestBody
      ExchangeRatesRq exchangeRatesRq) {
    exchangeRateManager.updateExchangeRates(exchangeRatesRq);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }
}
