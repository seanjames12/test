package com.bpdts.london;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LondonController.class)
@ContextConfiguration(classes = {LondonClient.class, APIConfig.class})
public class LondonControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private LondonServiceImpl londonServiceImpl;

  @Test
  void testGetLondonUsers_whenInvoked_returnStatusIsOkay() throws Exception {
    // Arrange
    User[] users = {
      User.builder()
          .email("email")
          .first_name("Sean")
          .id(1)
          .last_name("Rutherford")
          .ip_address("1")
          .latitude(1.00)
          .longitude(1.00)
          .build()
    };

    Mockito.when(londonServiceImpl.getLondonUsers(anyBoolean())).thenReturn(users);

    // Act Assert
    this.mockMvc
        .perform(get("/london").param("radius", "true"))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
  }
}
