package com.auto.ele.veh.model.response;

import java.util.List;
import com.auto.ele.veh.model.error.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class represents the Error Response Event for Insufficient Fuel Scenario
 * 
 * @author tejaskhapli
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsufficientFuelResponseEvent extends ErrorResponseEvent {


  protected InsufficientFuelResponseEvent(List<ApplicationError> ErrorList) {
    super(ErrorList);
  }

  private String vin;
  private String source;
  private String destination;
  private int distance;
  private int currentChargeLevel;
  private boolean isChargingRequired;

}
