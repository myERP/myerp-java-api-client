package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

import java.util.Date;
import java.util.List;

public class SalesOrder extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public String external_id;

  @Field
  public Integer customer;

  @Field
  public Integer billing_contact;

  @Field
  public Address billing_address;

  @Field
  public String user_code;

  @Field
  public String po_number;

  @Field
  public Date date;

  @Field
  public Integer tax;

  @Field
  public Integer currency;

  @Field
  public Double reduce_rate;

  @Field
  public List<OrderLine> order_lines;

  @Field
  public String public_note;

  @Field
  public Date shipping_date;

  @Field
  public Integer shipping_contact;

  @Field
  public Address shipping_address;

  @Field
  public Integer payment_terms;

  @Field
  public Integer project;

  @Field
  public Integer employee;

  @Field
  public String private_note;

  @Field
  public Double subtotal;

  @Field
  public Double reduce_amount;

  @Field
  public Double tax_amount;

  @Field
  public Double total;

}
