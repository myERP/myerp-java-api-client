package com.myerp.api.objects;

import com.myerp.api.MyERPObject;

public class Project extends MyERPObject {

  @Field
  public Integer id;

  @Field
  public String name;

  @Field
  public String description;

}
