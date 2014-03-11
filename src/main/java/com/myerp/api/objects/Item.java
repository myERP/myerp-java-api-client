package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

public class Item extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public String external_id;

  @Field
  public Integer type;

  @Field
  public String name;

  @Field
  public Integer unit;

  @Field
  public Double sales_price;

  @Field
  public String item_number;

  @Field
  public String description;

  @Field
  public Integer family;

  @Field
  public String private_note;

  @Field
  public Double purchase_price;

  @Field
  public Double net_weight;

  @Field
  public Double width;

  @Field
  public Double length;

  @Field
  public Double height;

  @Field
  public Integer dimension_unit;

}
