package com.example.Empik.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private int id;
    private String login;
    private String name;
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    private double calculations;

    public User(UserGithub userGithub) {
        this.id = userGithub.getId();
        this.login = userGithub.getLogin();
        this.name = userGithub.getName();
        this.type = userGithub.getType();
        this.avatarUrl = userGithub.getAvatarUrl();
        this.createdAt = userGithub.getCreatedAt();
        this.calculations = userGithub.getCalculations();
    }
    public User(){}
}
