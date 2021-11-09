package com.auto.ele.veh.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstrat Class represents the Response Event
 * 
 * @author tejaskhapli
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResponseEvent {

  private String transactionId;
}
