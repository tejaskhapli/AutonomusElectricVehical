package com.auto.ele.veh.model.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class Represents Source to Destinatioun Route model/entity
 * 
 * @author tejaskhapli
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Route {
  private String source;
  private String destination;
  private String error;
}
