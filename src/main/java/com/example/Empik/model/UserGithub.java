package com.example.Empik.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserGithub extends User{
    private int followers;
    @JsonProperty("public_repos")
    private int publicRepos;

    public UserGithub() {}

    public UserGithub(int followers, int publicRepos) {
        super();
        this.followers = followers;
        this.publicRepos = publicRepos;
    }
}


