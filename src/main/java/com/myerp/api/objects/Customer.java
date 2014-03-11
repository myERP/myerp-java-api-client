package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

import java.util.List;

public class Customer extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public Integer type;

  @Field
  public Integer status;

  @Field
  public String full_name;

  @Field
  public Address address;

  @Field
  public Address shipping_address;

  @Field
  public String first_name;

  @Field
  public String last_name;

  @Field
  public List<Contact> contacts;

}
