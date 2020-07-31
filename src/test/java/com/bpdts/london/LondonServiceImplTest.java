package com.bpdts.london;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LondonServiceImpl.class, LondonClientImpl.class, APIConfig.class})
public class LondonServiceImplTest {

  @Autowired private LondonServiceImpl londonService;

  @MockBean private LondonClientImpl londonClientImpl;

  @Test
  void testGetLondonUsers_whenRadiusIsFalse_callGetLondonUsers() {
    // Act
    londonService.getLondonUsers(Boolean.FALSE);

    // Assert
    verify(londonClientImpl, times(1)).getLondonUsers();
  }

  @Test
  void testGetLondonUsers_whenRadiusIsTrue_filterUsersOutOfRadius() {
    // Arrange
    User user1 =
        User.builder()
            .email("email")
            .first_name("Sean")
            .id(1)
            .last_name("Rutherford")
            .ip_address("1")
            .latitude(1.00)
            .longitude(1.00)
            .build();

    User user2 =
        User.builder()
            .email("email")
            .first_name("Sean")
            .id(1)
            .last_name("Rutherford")
            .ip_address("1")
            .latitude(51.6710832)
            .longitude(0.8078532)
            .build();

    User[] users = {user1, user2};

    User[] expectedResult = {user2};

    Mockito.when(londonClientImpl.getAllUsers()).thenReturn(users);

    // Act
    User[] result = londonService.getLondonUsers(Boolean.TRUE);

    // Assert
    assertThat(result).isEqualTo(expectedResult);
  }
}
