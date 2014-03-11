package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

public class Account extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public String number;

  @Field
  public String name;

  @Field
  public Boolean is_family;

  @Field
  public Integer family_id;

}
