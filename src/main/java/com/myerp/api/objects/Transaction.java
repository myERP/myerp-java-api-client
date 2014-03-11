package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

import java.util.Date;
import java.util.List;

public class Transaction extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public Integer transaction_code;

  @Field
  public Date date;

  @Field
  public Integer journal;

  @Field
  public String piece_number;

  @Field
  public List<AccountingEntry> entries;

}
