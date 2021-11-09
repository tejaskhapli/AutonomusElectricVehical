package com.auto.ele.veh.model.response;

import java.util.List;
import java.util.UUID;
import com.auto.ele.veh.model.error.ApplicationError;
import com.auto.ele.veh.model.request.RequestEvent;
import com.auto.ele.veh.model.route.ChargingStation;


/**
 * This class generates various Responses to the incoming request.
 * 
 * @author tejaskhapli
 *
 */
public class ResponseGenerator {

  private ResponseGenerator() {}

  public static ResponseEvent getSuccessRespEvent(RequestEvent reqEvent, List<ChargingStation> visitedStations,
          int currentChargeLevel, int distance) {
    String uniqueID = UUID.randomUUID().toString();
    if (visitedStations.isEmpty()) {
      SuccessNoChargingStnReqEvent e = new SuccessNoChargingStnReqEvent();
      e.setTransactionId(uniqueID);
      e.setSource(reqEvent.getSource());
      e.setDestination(reqEvent.getDestination());
      e.setVin(reqEvent.getVin());
      e.setCurrentChargeLevel(currentChargeLevel);
      e.setChargingRequired(!visitedStations.isEmpty());
      e.setDistance(distance);
      return e;

    } else {
      SuccessChargingStnReqEvent e = new SuccessChargingStnReqEvent();
      e.setTransactionId(uniqueID);
      e.setSource(reqEvent.getSource());
      e.setDestination(reqEvent.getDestination());
      e.setVin(reqEvent.getVin());
      e.setCurrentChargeLevel(currentChargeLevel);
      e.setChargingRequired(!visitedStations.isEmpty());
      e.setChargingStationList(visitedStations);
      e.setDistance(distance);
      return e;
    }

  }

  public static ResponseEvent getInsuffFuelRespEvent(List<ApplicationError> errorList, RequestEvent reqEvent,
          int curChargeLevel, int distance) {

    String uniqueID = UUID.randomUUID().toString();

    InsufficientFuelResponseEvent e = new InsufficientFuelResponseEvent();
    e.setChargingRequired(true);
    e.setCurrentChargeLevel(curChargeLevel);
    e.setTransactionId(uniqueID);
    e.setVin(reqEvent.getVin());
    e.setSource(reqEvent.getSource());
    e.setDestination(reqEvent.getDestination());
    e.setErrorList(errorList);
    e.setDistance(distance);

    return e;
  }

  public static ResponseEvent getTechnicalExceptionError(List<ApplicationError> errorList) {
    String uniqueID = UUID.randomUUID().toString();
    ErrorResponseEvent e = new ErrorResponseEvent();
    e.setErrorList(errorList);
    e.setTransactionId(uniqueID);

    return e;
  }

}
