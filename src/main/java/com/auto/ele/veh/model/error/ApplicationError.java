package com.auto.ele.veh.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * Model Class represents Errors in the Response Event
 * 
 * @author tejaskhapli
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApplicationError {

  private int id;
  private String description;
}
