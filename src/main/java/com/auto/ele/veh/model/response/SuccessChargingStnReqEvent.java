package com.auto.ele.veh.model.response;

import java.util.List;
import com.auto.ele.veh.model.route.ChargingStation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to represent Success Event with underlying charging stations required between Source and Destination.
 * 
 * @author tejaskhapli
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessChargingStnReqEvent extends SuccessResponseEvent{

  private List<ChargingStation> chargingStationList;
}
