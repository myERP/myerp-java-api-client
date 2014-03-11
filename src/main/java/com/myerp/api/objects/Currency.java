package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

public class Currency extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public String iso_code;

  @Field
  public String name;

  @Field
  public Boolean main_currency;

}
