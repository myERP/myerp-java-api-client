package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.Customer;

public class CustomersAPI extends API<Customer> {

  public CustomersAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, Customer.class);
  }

  @Override
  protected String getPath() {
    return "/customers";
  }

}
