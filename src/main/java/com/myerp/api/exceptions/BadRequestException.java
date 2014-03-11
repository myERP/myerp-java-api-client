package com.myerp.api.exceptions;


public class BadRequestException extends MyERPException {

  public BadRequestException(String code, String message, String reason) {
    super(code, message, reason);
  }

}
