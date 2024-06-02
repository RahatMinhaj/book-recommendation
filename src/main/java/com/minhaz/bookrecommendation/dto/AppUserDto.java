package com.minhaz.bookrecommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private Long userId;
    private String userName;
    private String email;
}
