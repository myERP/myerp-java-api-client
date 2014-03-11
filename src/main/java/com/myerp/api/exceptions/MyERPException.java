package com.myerp.api.exceptions;

public class MyERPException extends Exception {

  /**
   * Error code.
   */
  public final String code;

  /**
   * Error message.
   */
  public final String message;

  /**
   * Error reason.
   */
  public final String reason;

  public MyERPException(String code, String message, String reason) {
    super("API Error (" + code + "): " + message);
    this.code = code;
    this.message = message;
    this.reason = reason;
  }

  public String toString() {
    return super.toString() + "Reason=" + this.reason;
  }
}