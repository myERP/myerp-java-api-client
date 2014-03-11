package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.Item;

public class ItemsAPI extends API<Item> {

  public ItemsAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, Item.class);
  }

  @Override
  protected String getPath() {
    return "/items";
  }

}
