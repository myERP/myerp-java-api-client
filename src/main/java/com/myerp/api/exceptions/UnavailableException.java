package com.myerp.api.exceptions;


public class UnavailableException extends MyERPException {

  public UnavailableException(String code, String message, String reason) {
    super(code, message, reason);
  }

}
