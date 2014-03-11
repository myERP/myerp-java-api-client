package com.myerp.api.exceptions;


public class NotFoundException extends MyERPException {

  public NotFoundException(String code, String message, String reason) {
    super(code, message, reason);
  }

}
