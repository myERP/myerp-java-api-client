package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

public class AccountingEntry extends MyERPObject {

  @Field
  public String label;

  @Field
  public Double amount;

  @Field
  public String way;

  @Field
  public Integer account_id;

  @Field
  public String account_label;

  @Field
  public String lettering;

}
