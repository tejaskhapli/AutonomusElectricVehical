package com.auto.ele.veh.model.response;

import java.util.List;
import com.auto.ele.veh.model.error.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class represents the Error Response Event
 * 
 * @author tejaskhapli
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseEvent extends ResponseEvent{

  private List<ApplicationError> ErrorList;

}
