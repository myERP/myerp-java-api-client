package com.myerp.api.exceptions;


public class RateLimitedException extends MyERPException {

  public RateLimitedException(String code, String message, String reason) {
    super(code, message, reason);
  }
}