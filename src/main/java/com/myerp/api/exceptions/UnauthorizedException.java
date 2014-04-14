package com.myerp.api.exceptions;


public class UnauthorizedException extends MyERPException {

  public UnauthorizedException(String code, String message, String reason) {
    super(code, message, reason);
  }

}
