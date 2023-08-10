package com.example.Empik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class RequestCounter {

    @Id
    private String login;
    private int requestCount;

    public RequestCounter(String login, int requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

    public RequestCounter() {}
}


