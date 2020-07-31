package com.bpdts.london;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LondonController implements LondonContract {

  @Autowired private LondonServiceImpl service;

  @GetMapping(path = "/london", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User[]> getLondonUsers(@RequestParam(name = "radius") boolean radius) {
    return new ResponseEntity<>(this.service.getLondonUsers(radius), HttpStatus.OK);
  }
}
