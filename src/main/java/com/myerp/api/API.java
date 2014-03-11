package com.myerp.api;

import com.myerp.api.connection.HttpClientConnectionManager;
import com.myerp.api.exceptions.MyERPException;
import com.myerp.api.internal.gson.MyERPGsonFactory;

import org.apache.http.client.utils.URIBuilder;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public abstract class API<T extends MyERPObject> {

  private String apiEmail;
  private String apiKey;
  private String endpoint;
  private final Gson gson = MyERPGsonFactory.createGson();
  private Class<T> aClass;
  private HttpClientConnectionManager<T> connection;

  protected abstract String getPath();

  public API(String apiEmail, String apiKey, String endpoint, Class<T> aClass) {
    this.apiEmail = apiEmail;
    this.apiKey = apiKey;
    this.endpoint = endpoint;
    this.aClass = aClass;
    this.connection = new HttpClientConnectionManager<T>();
  }

  public List<T> all() throws MyERPException, IOException {
    return findAll();
  }

  public List<T> findAll() throws MyERPException, IOException {
    int limit = 100, page = 0;
    List<T> list = new ArrayList<T>();
    APIResponse<T> resp = null;
    do {
      URIBuilder builder = new URIBuilder();
      builder.setPath(this.getPath()).addParameter("offset", Integer.toString(page * limit))
	  .addParameter("limit", Integer.toString(limit));
      try {
	resp = execute("get", builder.build().toString());
      } catch (URISyntaxException e) {
	e.printStackTrace();
      }
      list.addAll(resp.getData(gson, aClass));
      page++;
    } while (resp.hasNextPage());
    return list;
  }

  public T find(int id) throws MyERPException, IOException {
    return execute("get", this.getPath() + "/" + id).getData(gson, aClass).get(0);
  }

  public T save(T object) throws MyERPException, IOException {
    int id = (Integer) (object.get("id") == null ? 0 : object.get("id"));
    if (id == 0) {
      return execute("post", this.getPath(), gson.toJsonTree(object).toString()).getData(gson, aClass).get(0);
    } else {
      return execute("put", this.getPath() + "/" + id, gson.toJsonTree(object).toString()).getData(gson, aClass).get(0);
    }
  }

  public T delete(T object) throws MyERPException, IOException {
    return execute("delete", this.getPath() + "/" + (object.get("id")), null).getData(gson, aClass).get(0);
  }

  public List<T> delete(List<T> objects) throws MyERPException, IOException {
    return execute("delete", this.getPath(), gson.toJsonTree(objects).toString()).getData(gson, aClass);
  }

  public List<T> save(List<T> objects) throws MyERPException, IOException {
    return execute("put", this.getPath(), gson.toJsonTree(objects).toString()).getData(gson, aClass);
  }

  private APIResponse<T> execute(String method, String url) throws IOException, MyERPException {
    return execute(method, url, null);
  }

  private APIResponse<T> execute(String method, String url, String payload) throws IOException, MyERPException {
    APIResponse<T> response = null;
    Method declaredMethod = null;
    try {
      declaredMethod =
	  connection.getClass().getDeclaredMethod(method, new Class[] { String.class, String.class, String.class });
      response =
	  (APIResponse<T>) declaredMethod.invoke(connection, this.endpoint + url, payload, this.apiEmail + ":"
	      + this.apiKey);
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    return response;

  }

  public HttpClientConnectionManager<T> getConnection() {
    return connection;
  }

  public void setConnection(HttpClientConnectionManager<T> connection) {
    this.connection = connection;
  }
}
