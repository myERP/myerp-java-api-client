package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.PaymentTerm;

public class PaymentTermsAPI extends API<PaymentTerm> {

  public PaymentTermsAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, PaymentTerm.class);
  }

  @Override
  protected String getPath() {
    return "/payment_terms";
  }

}
