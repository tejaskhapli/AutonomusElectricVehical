package com.auto.ele.veh.model.route;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class Represents Charging Station Route model/entity
 * 
 * @author tejaskhapli
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChargingStationRoute extends Route {

  private List<ChargingStation> chargingStations;
}
