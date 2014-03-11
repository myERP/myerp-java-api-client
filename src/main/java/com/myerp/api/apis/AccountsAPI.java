package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.Account;

public class AccountsAPI extends API<Account> {

  public AccountsAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, Account.class);
  }

  @Override
  protected String getPath() {
    return "/accounts";
  }

}
