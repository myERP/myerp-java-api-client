package com.myerp.api.apis;

import com.myerp.api.API;
import com.myerp.api.objects.Project;

public class ProjectsAPI extends API<Project> {

  public ProjectsAPI(String apiEmail, String apiKey, String endpoint) {
    super(apiEmail, apiKey, endpoint, Project.class);
  }

  @Override
  protected String getPath() {
    return "/projects";
  }

}
