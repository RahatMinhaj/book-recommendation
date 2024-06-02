package com.minhaz.bookrecommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistoryDto {
    private String searchingKeyword;
    private LocalDateTime searchingTime;
}
