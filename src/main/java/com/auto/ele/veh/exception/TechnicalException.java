package com.auto.ele.veh.exception;

/**
 * Exception Class for Technical Exception Scenario
 * 
 * @author tejaskhapli
 *
 */
public class TechnicalException extends Exception{

  private static final long serialVersionUID = 1L;

  public TechnicalException(String message) {
    super(message);
  }
}
