package com.biruntha.security.basicauth.models;

public class Token {
    private String id;

    public String getId() {
        return id;
    }

    public Token(String id) {
        super();
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
