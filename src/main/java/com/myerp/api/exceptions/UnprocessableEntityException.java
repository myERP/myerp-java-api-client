package com.myerp.api.exceptions;


public class UnprocessableEntityException extends MyERPException {

  public UnprocessableEntityException(String code, String message, String reason) {
    super(code, message, reason);
  }

}
