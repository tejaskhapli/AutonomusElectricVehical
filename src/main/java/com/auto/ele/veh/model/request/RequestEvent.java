package com.auto.ele.veh.model.request;

import lombok.Getter;

/**
 * Class specifies incoming request event.
 * 
 * @author tejaskhapli
 *
 */
@Getter
public class RequestEvent {

  private String vin;
  private String source;
  private String destination;
}
