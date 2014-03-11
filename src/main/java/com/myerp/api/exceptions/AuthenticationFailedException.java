package com.myerp.api.exceptions;


public class AuthenticationFailedException extends MyERPException {

  public AuthenticationFailedException(String code, String message, String reason) {
    super(code, message, reason);
  }

}
