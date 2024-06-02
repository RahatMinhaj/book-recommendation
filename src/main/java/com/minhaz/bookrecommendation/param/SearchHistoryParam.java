package com.minhaz.bookrecommendation.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistoryParam {
    private String searchingKeyword;
    private LocalDateTime searchingTime;
}
