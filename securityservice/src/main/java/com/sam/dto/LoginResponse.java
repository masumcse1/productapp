package com.sam.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String username;
    private String email;

    public LoginResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }


}
