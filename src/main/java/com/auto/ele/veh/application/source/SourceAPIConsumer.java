package com.auto.ele.veh.application.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.auto.ele.veh.application.common.RestClient;
import com.auto.ele.veh.exception.TechnicalException;
import com.auto.ele.veh.model.route.ChargingStationRoute;
import com.auto.ele.veh.model.route.DistanceRoute;
import com.auto.ele.veh.model.vehical.Vehicle;

/**
 * This class consists of methods which fetches the Vehical Details,
 * Source to Destination Route details and the Charging Stations present
 * between Source and Destination from Server : restmock.techgig.com.
 * 
 * @author tejaskhapli
 *
 */

@Component
public class SourceAPIConsumer {

  RestClient restClient;

  @Autowired
  public SourceAPIConsumer(RestClient restClient) {
    this.restClient = restClient;
  }

  public DistanceRoute fetchDistanceRoute(String source, String destination) throws TechnicalException {

    DistanceRoute route = restClient.fetchDistanceRoute(source, destination);

    if (route.getDestination() == null || route.getSource() == null) {
      throw new TechnicalException(route.getError());
    }

    return route;
  }

  public ChargingStationRoute fetchChargingStationRoute(String source, String destination) {

    return restClient.fetchChargingStationRoute(source, destination);

  }
  
  public Vehicle fetchVehical(String vin) throws TechnicalException {

    Vehicle vehical = restClient.fetch(vin);

    if (vehical.getVin() == null) {
      throw new TechnicalException(vehical.getError());
    }

    return vehical;
  }

}
