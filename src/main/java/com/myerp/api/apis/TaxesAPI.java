package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.Taxes;

public class TaxesAPI extends API<Taxes> {

  public TaxesAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, Taxes.class);
  }

  @Override
  protected String getPath() {
    return "/taxes";
  }

}
