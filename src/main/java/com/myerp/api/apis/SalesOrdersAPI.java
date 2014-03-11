package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.SalesOrder;

public class SalesOrdersAPI extends API<SalesOrder> {

  public SalesOrdersAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, SalesOrder.class);
  }

  @Override
  protected String getPath() {
    return "/sales_orders";
  }

}
