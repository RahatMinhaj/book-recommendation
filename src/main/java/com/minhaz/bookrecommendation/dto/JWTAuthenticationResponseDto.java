package com.minhaz.bookrecommendation.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JWTAuthenticationResponseDto {
    private String token;
    private String refreshToken;

}
