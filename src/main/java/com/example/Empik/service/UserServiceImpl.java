package com.example.Empik.service;

import com.example.Empik.model.RequestCounter;
import com.example.Empik.model.User;
import com.example.Empik.model.UserGithub;
import com.example.Empik.repository.RequestCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService{

    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    @Autowired
    RequestCounterRepository requestCounterRepository;

    @Override
    public User getUserInfo(String login) {

        RequestCounter requestCounter = requestCounterRepository.findByLogin(login);
        UserGithub user = getUserInfoFromGitHub(login);
        int followers = user.getFollowers();
        int publicRepos = user.getPublicRepos();
        double calculations = 6.0 / followers * (2 + publicRepos);
        user.setCalculations(calculations);

        if (requestCounter == null) {
            requestCounter = new RequestCounter(login, 1);
            requestCounterRepository.save(requestCounter);
        } else {
            requestCounter.setRequestCount(requestCounter.getRequestCount() + 1);
            requestCounterRepository.save(requestCounter);
        }

        return new User(user);
    }

    protected UserGithub getUserInfoFromGitHub(String login) {

        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = GITHUB_API_URL + login;

        try {
            ResponseEntity<UserGithub> response = restTemplate.getForEntity(apiUrl, UserGithub.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound notFoundException) {
            throw new RuntimeException("User not found on GitHub", notFoundException);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user information from GitHub API", e);
        }
    }
}

