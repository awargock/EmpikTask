package com.example.Empik.service;

import com.example.Empik.model.User;
import com.example.Empik.model.UserGithub;
import com.example.Empik.repository.RequestCounterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @MockBean
    private RequestCounterRepository requestCounterRepository;

    @MockBean
    private RestTemplate restTemplate;

    UserServiceImplForTest userService = new UserServiceImplForTest();

    @Test
    public void testGetUserInfoUser() {
        userService.requestCounterRepository = requestCounterRepository;

        String login = "octocat";

        User result = userService.getUserInfo(login);

        double calculations = 6.0 / 10064 * (2 + 8);
        assertEquals(String.format("%.5f", calculations), String.format("%.5f", result.getCalculations()));
    }

    @Test
    public void testUserNotFound() {
        userService.requestCounterRepository = requestCounterRepository;

        String login = "invalidUserxxx";

        Throwable exception = assertThrows(RuntimeException.class, () -> userService.getUserInfo(login));
        assertEquals("User not found on GitHub", exception.getMessage());
    }

    private static class UserServiceImplForTest extends UserServiceImpl {
        public UserGithub getUserInfoFromGitHubForTest(String login) {
            return super.getUserInfoFromGitHub(login);
        }
    }
}