package com.example.demo.dto.responses;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String role;

    public JwtResponse(String token, Long id, String username, String firstname, String lastname, String email, String role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String Token) {
        this.token = Token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
