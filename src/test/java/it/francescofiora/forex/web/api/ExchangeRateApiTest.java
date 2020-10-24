package it.francescofiora.forex.web.api;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.francescofiora.forex.dto.ExchangeRateType;
import it.francescofiora.forex.dto.ExchangeRatesRq;
import it.francescofiora.forex.dto.ExchangeRatesRs;
import it.francescofiora.forex.service.ExchangeRateService;
import it.francescofiora.forex.web.errors.NotFoundException;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ExchangeRateApi.class)
public class ExchangeRateApiTest {

  private static final String CODE = "CODE";
  private static final String EXCHANGERATES_URI = "/exchangerates";
  private static final String EXCHANGERATES_ID_URI = "/exchangerates/{id}";
  private static final String BAD_URI = "/exchangerat";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ExchangeRateService exchangeRateService;

  @Autowired
  private ObjectMapper mapper;

  private ExchangeRatesRq createExchangeRatesRq() {
    ExchangeRatesRq request = new ExchangeRatesRq();
    request.getExchangeRates()
        .add(new ExchangeRateType().id(CODE).uri(EXCHANGERATES_URI + "/" + CODE).valutaFrom("EUR")
            .valutaTo("USD").exchangeRate(1.14193).exchangeData("2018-11-17T16:12:03625Z"));
    return request;
  }

  @Test
  public void testAddExchangeRates() throws Exception {
    mvc.perform(post(new URI(EXCHANGERATES_URI)).contentType(APPLICATION_JSON)
        .content(mapper.writeValueAsString(createExchangeRatesRq())))
        .andExpect(status().isCreated());
  }

  private void addExchangeRatesBadRequest(ExchangeRatesRq request) throws Exception {
    mvc.perform(post(new URI(EXCHANGERATES_URI)).contentType(APPLICATION_JSON)
        .content(mapper.writeValueAsString(request))).andExpect(status().isBadRequest());
  }

  @Test
  public void testAddExchangeRatesError() throws Exception {
    // empty ExchangeRateType list
    ExchangeRatesRq request = new ExchangeRatesRq();
    addExchangeRatesBadRequest(request);

    // ExchangeRateType.ExchangeData
    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setExchangeData("");
    addExchangeRatesBadRequest(request);

    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setExchangeData(" ");
    addExchangeRatesBadRequest(request);

    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setExchangeData(null);
    addExchangeRatesBadRequest(request);

    // ExchangeRateType.ExchangeRate
    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setExchangeRate(null);
    addExchangeRatesBadRequest(request);

    // ExchangeRateType.ExchangeRate
    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setExchangeRate(0.0);
    addExchangeRatesBadRequest(request);

    // ExchangeRateType.Uri
    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setUri("");
    addExchangeRatesBadRequest(request);

    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setUri("  ");
    addExchangeRatesBadRequest(request);

    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setUri(null);
    addExchangeRatesBadRequest(request);

    // ExchangeRateType.ValutaFrom
    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setValutaFrom("");
    addExchangeRatesBadRequest(request);

    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setValutaFrom("  ");
    addExchangeRatesBadRequest(request);

    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setValutaFrom(null);
    addExchangeRatesBadRequest(request);

    // ExchangeRateType.ValutaTo
    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setValutaTo("");
    addExchangeRatesBadRequest(request);

    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setValutaTo("  ");
    addExchangeRatesBadRequest(request);

    request = createExchangeRatesRq();
    request.getExchangeRates().get(0).setValutaTo(null);
    addExchangeRatesBadRequest(request);
  }

  @Test
  public void testSearchExchangeRates() throws Exception {
    given(exchangeRateService.searchExchangeRates(anyString(), anyString(), anyString(),
        anyString(), anyInt(), anyInt())).willReturn(new ExchangeRatesRs());

    mvc.perform(get(EXCHANGERATES_URI)).andExpect(status().isOk());
  }

  @Test
  public void testSearchExchangeRate() throws Exception {
    given(exchangeRateService.searchExchangeRate(anyString())).willReturn(new ExchangeRateType());

    mvc.perform(get(EXCHANGERATES_ID_URI, CODE)).andExpect(status().isOk());
  }

  @Test()
  public void testSearchExchangeRateNotFound() throws Exception {
    given(exchangeRateService.searchExchangeRate(CODE)).willThrow(NotFoundException.class);

    mvc.perform(get(EXCHANGERATES_ID_URI, CODE)).andExpect(status().isNotFound());
  }

  @Test
  public void testUpdateExchangeRates() throws Exception {
    mvc.perform(put(new URI(EXCHANGERATES_URI)).contentType(APPLICATION_JSON)
        .content(mapper.writeValueAsString(createExchangeRatesRq())))
        .andExpect(status().isCreated());
  }

  @Test()
  public void testSearchExchangeRateWrongPath() throws Exception {
    mvc.perform(get(BAD_URI, CODE)).andExpect(status().isNotFound());
  }
}
