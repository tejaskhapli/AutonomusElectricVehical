package com.auto.ele.veh.application.request;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.auto.ele.veh.application.source.SourceAPIConsumer;
import com.auto.ele.veh.exception.InsufficientFuelException;
import com.auto.ele.veh.exception.TechnicalException;
import com.auto.ele.veh.model.error.ApplicationError;
import com.auto.ele.veh.model.request.RequestEvent;
import com.auto.ele.veh.model.response.ResponseEvent;
import com.auto.ele.veh.model.response.ResponseGenerator;
import com.auto.ele.veh.model.route.ChargingStation;
import com.auto.ele.veh.model.route.ChargingStationRoute;
import com.auto.ele.veh.model.route.DistanceRoute;
import com.auto.ele.veh.model.vehical.Vehicle;

/**
 * Intermediate class processes the incoming request.
 * 
 * @author tejaskhapli
 *
 */

@Component
public class RequestProcessor {

  SourceAPIConsumer apiConsumer;
  ChargingStationFinder chargingStationfinder;

  @Autowired
  public RequestProcessor(SourceAPIConsumer routeService, ChargingStationFinder chargingStationfinder) {
    this.apiConsumer = routeService;
    this.chargingStationfinder = chargingStationfinder;
  }

  public ResponseEvent process(RequestEvent reqEvent) {

    DistanceRoute dRoute;
    Vehicle v;
    ChargingStationRoute csRoute;
    List<ApplicationError> errorList = new ArrayList<>();

    try {

      v = apiConsumer.fetchVehical(reqEvent.getVin());
      dRoute = apiConsumer.fetchDistanceRoute(reqEvent.getSource(), reqEvent.getDestination());

    } catch (TechnicalException ex) {
      int errorCode = 9999;
      errorList.add(new ApplicationError(errorCode, "Technical Exception. " + ex.getMessage()));
      return ResponseGenerator.getTechnicalExceptionError(errorList);

    }

    csRoute = apiConsumer.fetchChargingStationRoute(reqEvent.getSource(), reqEvent.getDestination());

    int distance = dRoute.getDistance();
    int initialChargingLevel = v.getCurrentChargeLevel();
    int curChargeingLevel = initialChargingLevel;

    List<ChargingStation> visitedStations = new ArrayList<>();

    List<ChargingStation> csList =
            csRoute.getChargingStations() == null ? new ArrayList<>() : csRoute.getChargingStations();
    csList.add(0, new ChargingStation("Source", 0, curChargeingLevel));
    csList.add(new ChargingStation("Destination", distance, 0));

    try {
      visitedStations = chargingStationfinder.getVisitingStations(csList);
    } catch (InsufficientFuelException ex) {

      int errorCode = 8888;
      errorList.add(new ApplicationError(errorCode, ex.getMessage()));
      return ResponseGenerator.getInsuffFuelRespEvent(errorList, reqEvent, v.getCurrentChargeLevel(), distance);
    }

    return ResponseGenerator.getSuccessRespEvent(reqEvent, visitedStations, v.getCurrentChargeLevel(), distance);
  }

}
