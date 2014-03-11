package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.ItemFamily;

public class ItemFamiliesAPI extends API<ItemFamily> {

  public ItemFamiliesAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, ItemFamily.class);
  }

  @Override
  protected String getPath() {
    return "/item_families";
  }

}
