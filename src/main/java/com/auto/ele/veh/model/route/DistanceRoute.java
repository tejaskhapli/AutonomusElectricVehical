package com.auto.ele.veh.model.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class Represents Distance Route model/entity
 * 
 * @author tejaskhapli
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DistanceRoute extends Route {

  private int distance;
}
