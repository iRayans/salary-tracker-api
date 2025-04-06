package com.rayan.salarytracker.dto.user;

public class AuthenticationResponseDTO {

    private String token;
    private UserReadOnlyDTO user;

    public AuthenticationResponseDTO(String token, UserReadOnlyDTO user) {
        this.token = token;
        this.user = user;
    }

    public AuthenticationResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserReadOnlyDTO getUser() {
        return user;
    }

    public void setUser(UserReadOnlyDTO user) {
        this.user = user;
    }
}
