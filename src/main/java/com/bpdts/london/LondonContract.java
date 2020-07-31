package com.bpdts.london;

import org.springframework.http.ResponseEntity;

public interface LondonContract {
  ResponseEntity<User[]> getLondonUsers(boolean radius);
}
