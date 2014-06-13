package com.myerp.api;

import com.myerp.api.exceptions.BadRequestException;
import com.myerp.api.exceptions.MyERPException;
import com.myerp.api.exceptions.NotFoundException;
import com.myerp.api.exceptions.RateLimitedException;
import com.myerp.api.exceptions.ServerErrorException;
import com.myerp.api.exceptions.UnauthorizedException;
import com.myerp.api.exceptions.UnavailableException;
import com.myerp.api.exceptions.UnprocessableEntityException;

import org.apache.http.Header;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class APIResponse<T> {

  private Header[] headers;
  private String body;
  private int httpCode;

  public APIResponse(int httpCode, Header[] headers, String body) {
    this.setHttpCode(httpCode);
    this.setHeaders(headers);
    this.setBody(body);
  }

  public List<T> getData(Gson gson, Class<T> aClass) throws MyERPException {

    List<T> res = new ArrayList<T>();
    JsonElement result = new JsonParser().parse(this.getBody());
    if (result.isJsonObject()) {
      JsonElement error = result.getAsJsonObject().get("error");
      if (error != null) {
        JsonObject errorObj = error.getAsJsonObject();
        String code = errorObj.get("code").getAsString();
        String message = errorObj.get("message").getAsString();
        String reason = (errorObj.get("reason") == null) ? "" : errorObj.get("reason").getAsString();
        switch (this.getHttpCode()) {
          case 400:
            throw new BadRequestException(code, message, reason);
          case 401:
            throw new UnauthorizedException(code, message, reason);
          case 404:
            throw new NotFoundException(code, message, reason);
          case 422:
            throw new UnprocessableEntityException(code, message, reason);
          case 429:
            throw new RateLimitedException(code, message, reason);
          case 500:
            throw new ServerErrorException(code, message, reason);
          case 502:
            throw new UnavailableException(code, message, reason);
          default:
            throw new MyERPException(code, message, reason);
        }
      }
      res.add(gson.fromJson(result, aClass));
    }
    if (result.isJsonArray()) {
      Iterator iterator = result.getAsJsonArray().iterator();
      while (iterator.hasNext()) {
        res.add(gson.fromJson((JsonElement) iterator.next(), aClass));
      }
    }
    return res;
  }

  public boolean hasNextPage() {
    for (Header header : this.getHeaders()) {
      if ("X-MyERP-Has-Next-Page".equals(header.getName())) {
        return "true".equals(header.getValue());
      }
    }
    return false;
  }

  public Header[] getHeaders() {
    return headers;
  }

  public void setHeaders(Header[] headers) {
    this.headers = headers;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public int getHttpCode() {
    return httpCode;
  }

  public void setHttpCode(int httpCode) {
    this.httpCode = httpCode;
  }

}
