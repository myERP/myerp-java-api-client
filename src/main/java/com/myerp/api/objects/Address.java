package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

public class Address extends MyERPObject {

  @Field
  public String label1;

  @Field
  public String label2;

  @Field
  public String label3;

  @Field
  public String zipcode;

  @Field
  public String city;

  @Field
  public String country;

  @Field
  public String localisation;

  @Field
  public String short_localisation;

  @Field
  public String full_address;

}
