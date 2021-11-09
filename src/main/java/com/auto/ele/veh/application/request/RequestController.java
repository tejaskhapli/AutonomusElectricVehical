package com.auto.ele.veh.application.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.auto.ele.veh.model.request.RequestEvent;
import com.auto.ele.veh.model.response.ResponseEvent;

/**
 * Starting Point of application.
 * Exposed REST API is present in this class : /process
 * 
 * @author tejaskhapli
 *
 */

@RestController
public class RequestController {

  RequestProcessor processor;


  @Autowired
  public RequestController(RequestProcessor processor) {
    this.processor = processor;
  }

  @PostMapping(value = "/process")
  public ResponseEntity<ResponseEvent> process(@RequestBody RequestEvent event) {

    ResponseEvent e = this.processor.process(event);

    ResponseEntity<ResponseEvent> response = ResponseEntity.ok(e);

    return response;
  }

}
