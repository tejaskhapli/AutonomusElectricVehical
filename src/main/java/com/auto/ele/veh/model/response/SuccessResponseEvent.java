package com.auto.ele.veh.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract Class to represent Success Event
 * 
 * @author tejaskhapli
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class SuccessResponseEvent extends ResponseEvent {

  private String vin;
  private String source;
  private String destination;
  private int distance;
  private int currentChargeLevel;
  private boolean isChargingRequired;

}
