package com.myerp.api.exceptions;


public class ServerErrorException extends MyERPException {

  public ServerErrorException(String code, String message, String reason) {
    super(code, message, reason);
  }

}
