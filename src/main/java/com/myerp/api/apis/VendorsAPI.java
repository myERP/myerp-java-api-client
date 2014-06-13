package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.Vendor;

public class VendorsAPI extends API<Vendor> {

  public VendorsAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, Vendor.class);
  }

  @Override
  protected String getPath() {
    return "/vendors";
  }

}
