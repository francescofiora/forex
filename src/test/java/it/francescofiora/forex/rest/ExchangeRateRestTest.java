package it.francescofiora.forex.rest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.francescofiora.forex.manager.ExchangeRateManager;
import it.francescofiora.forex.model.ExchangeRateType;
import it.francescofiora.forex.model.ExchangeRatesRq;
import it.francescofiora.forex.model.ExchangeRatesRs;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExchangeRateRest.class)
public class ExchangeRateRestTest {

  private static final String CODE = "CODE";
  private static final String EXCHANGERATES_URI = "/exchangerates";
  private static final String EXCHANGERATES_ID_URI = "/exchangerates/{id}";
  
  @Autowired
  private MockMvc mvc;

  @MockBean
  private ExchangeRateManager exchangeRateManager;

  @Autowired
  private ObjectMapper mapper;
  
  @Test
  public void testAddExchangeRates() throws Exception {
    mvc.perform(post(new URI(EXCHANGERATES_URI))
        .contentType(APPLICATION_JSON)
        .content(mapper.writeValueAsString(new ExchangeRatesRq())))
        .andExpect(status().isCreated());
  }

  @Test
  public void testSearchExchangeRates() throws Exception {
    given(exchangeRateManager.searchExchangeRates(anyString(), anyString(), anyString(), anyString(),
        anyInt(), anyInt()))
      .willReturn(new ExchangeRatesRs());

    mvc.perform(get(EXCHANGERATES_URI))
      .andExpect(status().isOk());
  } 

  @Test
  public void testSearchExchangeRate() throws Exception {
    given(exchangeRateManager.searchExchangeRate(anyString()))
      .willReturn(new ExchangeRateType());

    mvc.perform(get(EXCHANGERATES_ID_URI, CODE))
      .andExpect(status().isOk());
  } 

  @Test
  public void testUpdateExchangeRates() throws Exception {
    mvc.perform(put(new URI(EXCHANGERATES_URI))
        .contentType(APPLICATION_JSON)
        .content(mapper.writeValueAsString(new ExchangeRatesRq())))
        .andExpect(status().isCreated());
  } 
}
