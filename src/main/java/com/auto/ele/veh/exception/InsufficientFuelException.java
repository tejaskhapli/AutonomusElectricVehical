package com.auto.ele.veh.exception;

/**
 * Exception Class for Insufficient Fuel Scenario
 * 
 * @author tejaskhapli
 *
 */
public class InsufficientFuelException extends Exception {

  private static final long serialVersionUID = 1L;

  public InsufficientFuelException(String message) {
    super(message);
  }

}
