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
  public String phone_number;

  @Field
  public String email;

  @Field
  public String website;

  @Field
  public List<Contact> contacts;

  @Field
  public Integer payment_terms_id;

  @Field
  public String currency_iso_code;

  @Field
  public Integer sales_tax_id;

  @Field
  public String user_code;

  @Field
  public Boolean opt_in_to_emails;

  @Field
  public String comments;

  //company only
  @Field
  public String company_name;

  @Field
  public Integer employee_count;

  @Field
  public String taxpayer_id_number;

  @Field
  public String siret;

  //individual only
  @Field
  public String first_name;

  @Field
  public String middle_name;

  @Field
  public String last_name;

}
