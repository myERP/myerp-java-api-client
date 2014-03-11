package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

public class Contact extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public String first_name;

  @Field
  public String last_name;

  @Field
  public String full_name;

  @Field
  public String cell_number;

  @Field
  public String work_phone;

  @Field
  public String home_phone;

  @Field
  public String email;

  @Field
  public Address address;

  @Field
  public String notes;

  @Field
  //deprecated
  public Integer customer_id;
}
