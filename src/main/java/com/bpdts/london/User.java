package com.bpdts.london;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private int id;
  private String first_name;
  private String last_name;
  private String email;
  private String ip_address;
  private double latitude;
  private double longitude;
}
