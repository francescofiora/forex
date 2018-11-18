package it.francescofiora.forex.manager.impl;

import it.francescofiora.forex.manager.ExchangeRateManager;
import it.francescofiora.forex.model.ExchangeRateType;
import it.francescofiora.forex.model.ExchangeRatesRq;
import it.francescofiora.forex.model.ExchangeRatesRs;

import org.springframework.stereotype.Service;

@Service
public class ExchangeRateManagerImpl implements ExchangeRateManager {

  @Override
  public void addExchangeRates(ExchangeRatesRq exchangeRatesRq) {
  }

  @Override
  public ExchangeRateType searchExchangeRate(String exchangerateId) {
    ExchangeRateType exchangeRateType = new ExchangeRateType();
    exchangeRateType.setValutaFrom("EUR");
    exchangeRateType.setValutaTo("USD");
    exchangeRateType.setExchangeData("2018-11-17T16:12:03625Z");
    exchangeRateType.setExchangeRate(Double.valueOf("1.14193"));
    exchangeRateType.setId("EUR-USD-2018-11-17T161203625Z");
    exchangeRateType.setUri("/exchangerates/EUR-USD-2018-11-17T161203625Z");
    return exchangeRateType;
  }

  @Override
  public ExchangeRatesRs searchExchangeRates(String dataFrom, String dataTo, String valutaFrom, String valutaTo,
      Integer skip, Integer limit) {
    ExchangeRateType exchangeRateType = new ExchangeRateType();
    exchangeRateType.setValutaFrom("EUR");
    exchangeRateType.setValutaTo("USD");
    exchangeRateType.setExchangeData("2018-11-17T16:12:03625Z");
    exchangeRateType.setExchangeRate(Double.valueOf("1.14193"));
    exchangeRateType.setId("EUR-USD-2018-11-17T161203625Z");
    exchangeRateType.setUri("/exchangerates/EUR-USD-2018-11-17T161203625Z");

    ExchangeRatesRs exchangeRatesRs = new ExchangeRatesRs();
    exchangeRatesRs.setItem(1);
    exchangeRatesRs.setPage(1);
    exchangeRatesRs.setTotalItem(1);
    exchangeRatesRs.addExchangeRatesItem(exchangeRateType);
    return exchangeRatesRs;
  }

  @Override
  public void updateExchangeRates(ExchangeRatesRq forexDataRq) {
  }

}
