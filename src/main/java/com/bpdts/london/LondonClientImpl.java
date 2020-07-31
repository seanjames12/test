package com.bpdts.london;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LondonClientImpl implements LondonClient {

  private static final String BPDTS_BASE_URL = "https://bpdts-test-app.herokuapp.com";
  private static final String LONDON_USERS_URL = "/city/London/users";
  private static final String ALL_USERS_URL = "/users";

  @Autowired private RestTemplate restTemplate;

  public User[] getLondonUsers() {
    return restTemplate.getForObject(BPDTS_BASE_URL + LONDON_USERS_URL, User[].class);
  }

  public User[] getAllUsers() {
    return restTemplate.getForObject(BPDTS_BASE_URL + ALL_USERS_URL, User[].class);
  }
}
