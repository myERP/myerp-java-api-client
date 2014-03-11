package com.myerp.api.exceptions;


public class MissingFieldException extends MyERPException {

  public MissingFieldException(String code, String message, String reason) {
    super(code, message, reason);
  }

}
