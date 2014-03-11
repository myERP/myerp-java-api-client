package com.myerp.api.connection;

import com.myerp.api.APIResponse;

import java.io.Closeable;
import java.io.IOException;

public interface MyERPConnectionManager<T> extends Closeable {

  public APIResponse<T> get(String url, String payload, String basicAuth) throws IOException;

  public APIResponse<T> put(String url, String payload, String basicAuth) throws IOException;

  public APIResponse<T> post(String url, String payload, String basicAuth) throws IOException;

  public APIResponse<T> delete(String url, String payload, String basicAuth) throws IOException;
}
