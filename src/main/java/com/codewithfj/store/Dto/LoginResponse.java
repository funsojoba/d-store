package com.codewithfj.store.Dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String name;
    private String email;
    private String accessToken;
}
