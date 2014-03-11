package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.Transaction;

public class TransactionsAPI extends API<Transaction> {

  public TransactionsAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, Transaction.class);
  }

  @Override
  protected String getPath() {
    return "/transactions";
  }

}
