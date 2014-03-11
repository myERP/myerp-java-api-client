package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

public class OrderLine extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public Integer item;

  @Field
  public String description;

  @Field
  public Double quantity;

  @Field
  public Double unit_price_wt;

  @Field
  public Double discount_rate;

  @Field
  public Integer tax;

  @Field
  public Boolean is_taxable;

  @Field
  public Double total;
}
