package com.auto.ele.veh.application.common;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.auto.ele.veh.exception.TechnicalException;
import com.auto.ele.veh.model.route.ChargingStationRoute;
import com.auto.ele.veh.model.route.DistanceRoute;
import com.auto.ele.veh.model.vehical.Vehicle;

@Component
public class RestClient {

  RestTemplate restTemplate;

  @Autowired
  public RestClient() {
    this.restTemplate = new RestTemplate();
  }

  public ChargingStationRoute fetchChargingStationRoute(String source, String destination) {

    HttpEntity<String> entity = new HttpEntity<>(getRouteJson(source, destination), getHeaders());

    ResponseEntity<ChargingStationRoute> route = restTemplate.exchange(
            "https://restmock.techgig.com/merc/charging_stations", HttpMethod.POST, entity, ChargingStationRoute.class);

    return route.getBody();
  }

  public DistanceRoute fetchDistanceRoute(String source, String destination) throws TechnicalException {

    HttpEntity<String> entity = new HttpEntity<>(getRouteJson(source, destination), getHeaders());

    ResponseEntity<DistanceRoute> route = restTemplate.exchange("https://restmock.techgig.com/merc/distance",
            HttpMethod.POST, entity, DistanceRoute.class);

    if (route.getBody().getDestination() == null || route.getBody().getSource() == null) {
      throw new TechnicalException(route.getBody().getError());
    }

    return route.getBody();
  }

  public Vehicle fetch(String vin) throws TechnicalException {

    HttpEntity<String> entity = new HttpEntity<>(getVehicalJson(vin), getHeaders());

    ResponseEntity<Vehicle> vehical = restTemplate.exchange("https://restmock.techgig.com/merc/charge_level",
            HttpMethod.POST, entity, Vehicle.class);

    if (vehical.getBody().getVin() == null) {
      throw new TechnicalException(vehical.getBody().getError());
    }

    return vehical.getBody();
  }

  private HttpHeaders getHeaders() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return headers;
  }

  private String getRouteJson(String source, String destination) {

    JSONObject obj = new JSONObject();
    obj.put("source", source);
    obj.put("destination", destination);

    return obj.toString();
  }

  private String getVehicalJson(String vin) {
    JSONObject obj = new JSONObject();
    obj.put("vin", vin);

    return obj.toString();
  }

}
