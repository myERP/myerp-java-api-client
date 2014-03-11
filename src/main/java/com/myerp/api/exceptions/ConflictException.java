package com.myerp.api.exceptions;


public class ConflictException extends MyERPException {

  public ConflictException(String code, String message, String reason) {
    super(code, message, reason);
  }

}
