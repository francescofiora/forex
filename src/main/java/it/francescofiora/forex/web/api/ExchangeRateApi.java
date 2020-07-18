package it.francescofiora.forex.web.api;

import it.francescofiora.forex.dto.ExchangeRateType;
import it.francescofiora.forex.dto.ExchangeRatesRq;
import it.francescofiora.forex.dto.ExchangeRatesRs;
import it.francescofiora.forex.service.ExchangeRateService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Exchange Rate Rest Api.
 * 
 * @author francesco.fiora
 */
@RestController
@Tag(name = "exchangerates", description = "The exchangerates Rest API")
public class ExchangeRateApi {

	private final ExchangeRateService exchangeRateService;

	public ExchangeRateApi(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}

	@Operation(summary = "adds exchange rates", description = "adds exchange rate to the system", tags = { "forex" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "items created"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid"),
			@ApiResponse(responseCode = "409", description = "an existing item already exists") })
	@PostMapping(value = "/exchangerates", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<Void> addExchangeRates(
			@Parameter(description = "adds list of exchange rate") @Valid @RequestBody ExchangeRatesRq exchangeRatesRq) {
		exchangeRateService.addExchangeRates(exchangeRatesRq);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "searches exchange rate by 'exchangerateId'", description = "searches exchange rate by 'exchangerateId'", tags = { "forex" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "search results matching criteria", content = @Content(schema = @Schema(implementation = ExchangeRateType.class))),
			@ApiResponse(responseCode = "400", description = "bad input parameter") })
	@GetMapping(value = "/exchangerates/{exchangerateId}", produces = { "application/json" })
	ResponseEntity<?> searchExchangeRate(
			@Parameter(description = "ID of the exchange rate to get", required = true, example = "EUR-USD-2018-11-17T161203625Z") @PathVariable("exchangerateId") String exchangerateId) {
		return new ResponseEntity<>(exchangeRateService.searchExchangeRate(exchangerateId), HttpStatus.OK);
	}

	@Operation(summary = "searches exchange rates", description = "By passing in the appropriate options, you can search for available exchange rates in the system", tags = { "forex" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "search results matching criteria", content = @Content(schema = @Schema(implementation = ExchangeRatesRs.class))),
			@ApiResponse(responseCode = "400", description = "bad input parameter") })
	@GetMapping(value = "/exchangerates", produces = { "application/json" })
	ResponseEntity<?> searchExchangeRates(
			@Parameter(description = "pass an optional search for filter by data and time") @Valid @RequestParam(value = "dataFrom", required = false) String dataFrom,
			@Parameter(description = "pass an optional search for filter by data and type") @Valid @RequestParam(value = "dataTo", required = false) String dataTo,
			@Parameter(description = "pass an optional search for filter by valutaFrom", example = "EUR") @Valid @RequestParam(value = "valutaFrom", required = false) String valutaFrom,
			@Parameter(description = "pass an optional search for filter by valutaTo", example = "USD") @Valid @RequestParam(value = "valutaTo", required = false) String valutaTo,
			@Min(0) @Parameter(description = "number of records to skip for pagination", example = "0") @Valid @RequestParam(value = "skip", required = false) Integer skip,
			@Min(50) @Max(100) @Parameter(description = "maximum number of records to return", example = "50") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
		return new ResponseEntity<>(
				exchangeRateService.searchExchangeRates(dataFrom, dataTo, valutaFrom, valutaTo, skip, limit), HttpStatus.OK);
	}

	@Operation(summary = "updates exchange rates", description = "updates exchange rates to the system", tags = { "forex" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "items updated"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid") })
	@PutMapping(value = "/exchangerates", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<Void> updateExchangeRates(
			@Parameter(description = "updates list of exchange rate") @Valid @RequestBody ExchangeRatesRq exchangeRatesRq) {
		exchangeRateService.updateExchangeRates(exchangeRatesRq);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
