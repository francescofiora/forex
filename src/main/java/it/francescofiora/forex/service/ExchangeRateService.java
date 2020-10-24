package it.francescofiora.forex.service;

import it.francescofiora.forex.dto.ExchangeRateType;
import it.francescofiora.forex.dto.ExchangeRatesRq;
import it.francescofiora.forex.dto.ExchangeRatesRs;

public interface ExchangeRateService {

  /**
   * add ExchangeRate.
   * 
   * @param exchangeRatesRq ExchangeRatesRq
   */
  void addExchangeRates(ExchangeRatesRq exchangeRatesRq);

  /**
   * return ExchangeRate by id.
   * 
   * @param exchangerateId
   * @return
   */
  ExchangeRateType searchExchangeRate(String exchangerateId);

  /**
   * search ExchangeRate.
   * 
   * @param dataFrom
   * @param dataTo
   * @param valutaFrom
   * @param valutaTo
   * @param skip
   * @param limit
   * @return ExchangeRatesRs
   */
  ExchangeRatesRs searchExchangeRates(String dataFrom, String dataTo, String valutaFrom,
      String valutaTo, Integer skip, Integer limit);

  /**
   * update ExchangeRate.
   * 
   * @param forexDataRq ExchangeRatesRq
   */
  void updateExchangeRates(ExchangeRatesRq forexDataRq);
}
