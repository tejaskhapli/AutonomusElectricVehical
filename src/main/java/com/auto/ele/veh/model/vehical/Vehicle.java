package com.auto.ele.veh.model.vehical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class Represents Vehicle model/entity
 * 
 * @author tejaskhapli
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

  private String vin;
  private int currentChargeLevel;
  private String error;
  
}
