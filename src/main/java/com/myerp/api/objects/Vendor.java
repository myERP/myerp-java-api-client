package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

import java.util.List;

public class Vendor extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public Integer type;

  @Field
  public String full_name;

  @Field
  public Address address;

  @Field
  public String phone_number;

  @Field
  public String email;

  @Field
  public String website;

  @Field
  public List<Contact> contacts;

  @Field
  public String currency_iso_code;

  @Field
  public Integer payment_terms_id;

  @Field
  public String taxpayer_id_number;

  @Field
  public String user_code;

  @Field
  public String comments;

  //company only
  @Field
  public String company_name;

  //individual only
  @Field
  public String first_name;

  @Field
  public String middle_name;

  @Field
  public String last_name;

}
