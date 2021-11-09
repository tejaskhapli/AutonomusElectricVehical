package com.auto.ele.veh.model.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class Represents Charging Station model/entity
 * 
 * @author tejaskhapli
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChargingStation {

  private String name;
  private int distance;
  private int limit;
}
