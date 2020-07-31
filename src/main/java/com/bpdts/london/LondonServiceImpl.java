package com.bpdts.london;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LondonServiceImpl implements LondonService {

  private static final Double LONDON_LATITUDE = 51.50853;
  private static final Double LONDON_LONGITUDE = 0.12574;
  private static final Integer MAX_RADIUS = 50;

  @Autowired private LondonClientImpl londonClient;

  public User[] getLondonUsers(final boolean radius) {
    return (radius) ? filterUsersOutsideRadius() : this.londonClient.getLondonUsers();
  }

  private User[] filterUsersOutsideRadius() {
    return Arrays.stream(this.londonClient.getAllUsers())
        .filter(c -> getMilesFromLondon(c.getLatitude(), c.getLongitude()) < MAX_RADIUS)
        .toArray(User[]::new);
  }

  private double getMilesFromLondon(double latitude, double longitude) {
    if ((latitude == LONDON_LATITUDE) && (longitude == LONDON_LONGITUDE)) {
      return 0;
    }

    double theta = longitude - LONDON_LONGITUDE;

    double distance =
        Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(LONDON_LATITUDE))
            + Math.cos(Math.toRadians(latitude))
                * Math.cos(Math.toRadians(LONDON_LATITUDE))
                * Math.cos(Math.toRadians(theta));

    distance = Math.acos(distance);
    distance = Math.toDegrees(distance);
    return distance * 60 * 1.1515;
  }
}
