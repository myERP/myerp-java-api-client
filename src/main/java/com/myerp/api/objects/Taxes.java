package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

public class Taxes extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public String name;

  @Field
  public Double rate;

}
