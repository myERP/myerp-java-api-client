package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.Units;

public class UnitsAPI extends API<Units> {

  public UnitsAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, Units.class);
  }

  @Override
  protected String getPath() {
    return "/units";
  }

}
