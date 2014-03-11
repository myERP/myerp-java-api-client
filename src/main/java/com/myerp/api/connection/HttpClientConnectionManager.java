package com.myerp.api.connection;

import com.myerp.api.APIResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Implementation of {@link MyERPConnectionManager} which uses Apache HttpClient library to access myERP API
 * service point.
 *
 * @author Vasily Karyaev <v.karyaev@gmail.com>
 */
public class HttpClientConnectionManager<T> implements MyERPConnectionManager {

  private static final int DEFAULT_TIMEOUT = 15000;

  private final HttpClient http = new DefaultHttpClient();

  /**
   * Constructor.
   * Equivalent to calling {@link #HttpClientConnectionManager(int, int)} with both parameters set to 15000 (15
   * seconds).
   */
  public HttpClientConnectionManager() {
    this(DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
  }

  /**
   * Constructor.
   *
   * @param connectTimeout
   *          the timeout (in milliseconds) when trying to connect to the remote server
   * @param readTimeout
   *          the timeout (in milliseconds) when when waiting for the response from the remote server
   */
  public HttpClientConnectionManager(int connectTimeout, int readTimeout) {
    setConnectTimeout(connectTimeout);
    setReadTimeout(readTimeout);
  }

  private APIResponse<T> execute(HttpRequestBase request, String basicAuth) throws IOException {
    request.setHeader("Authorization", "Basic " + new String(Base64.encodeBase64(basicAuth.getBytes())));
    request.setHeader("Content-Type", "application/json");
    HttpResponse response = http.execute(request);
    if (response.getEntity() != null) {
      return new APIResponse<T>(response.getStatusLine().getStatusCode(), response.getAllHeaders(), EntityUtils
	  .toString(response.getEntity(), "UTF-8").trim());
    } else {
      throw new IOException(response.getStatusLine().toString());
    }
  }

  @Override
  public APIResponse<T> get(String url, String payload, String basicAuth) throws IOException {
    return execute(new HttpGet(url), basicAuth);
  }

  @Override
  public APIResponse<T> put(String url, String payload, String basicAuth) throws IOException {
    HttpPut put = new HttpPut(url);
    if (payload != null) {
      put.setEntity(new StringEntity(payload, "UTF-8"));
    }
    return execute(put, basicAuth);
  }

  @Override
  public APIResponse<T> delete(String url, String payload, String basicAuth) throws IOException {
    MyERPHttpDelete delete = new MyERPHttpDelete(url);
    if (payload != null) {
      delete.setEntity(new StringEntity(payload, "UTF-8"));
    }
    return execute(delete, basicAuth);
  }

  @Override
  public APIResponse<T> post(String url, String payload, String basicAuth) throws IOException {
    HttpPost post = new HttpPost(url);
    if (payload != null) {
      post.setEntity(new StringEntity(payload, "UTF-8"));
    }
    return execute(post, basicAuth);
  }

  @Override
  public void close() {
    http.getConnectionManager().shutdown();
  }

  public int getConnectTimeout() {
    return HttpConnectionParams.getConnectionTimeout(http.getParams());
  }

  public void setConnectTimeout(int connectTimeout) {
    HttpConnectionParams.setConnectionTimeout(http.getParams(), connectTimeout);
  }

  public int getReadTimeout() {
    return HttpConnectionParams.getSoTimeout(http.getParams());
  }

  public void setReadTimeout(int readTimeout) {
    HttpConnectionParams.setSoTimeout(http.getParams(), readTimeout);
  }

  /**
   * HttpDelete doesn't have the setEntity method to set the body
   * Monkey patch: use a post by renaming the method name
   */
  class MyERPHttpDelete extends HttpPost {
    public MyERPHttpDelete(String url) {
      super(url);
    }

    @Override
    public String getMethod() {
      return "DELETE";
    }
  }
}
