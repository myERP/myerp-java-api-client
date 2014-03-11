package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.Currency;

public class CurrenciesAPI extends API<Currency> {

  public CurrenciesAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, Currency.class);
  }

  @Override
  protected String getPath() {
    return "/currencies";
  }

}
