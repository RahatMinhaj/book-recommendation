package com.minhaz.bookrecommendation.service;

import com.minhaz.bookrecommendation.dto.AppUserDto;
import com.minhaz.bookrecommendation.dto.JWTAuthenticationResponseDto;
import com.minhaz.bookrecommendation.dto.SearchHistoryDto;
import com.minhaz.bookrecommendation.entity.AppUser;
import com.minhaz.bookrecommendation.entity.SearchHistory;
import com.minhaz.bookrecommendation.entity.UserRoles;
import com.minhaz.bookrecommendation.param.AppUserParam;
import com.minhaz.bookrecommendation.param.LoginParam;
import com.minhaz.bookrecommendation.param.RefreshTokenRequestParam;
import com.minhaz.bookrecommendation.param.SearchHistoryParam;
import com.minhaz.bookrecommendation.repository.SearchHistoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class SearchHistoryService {
    private final SearchHistoryRepository searchHistoryRepository;

    @Autowired
    public SearchHistoryService(SearchHistoryRepository searchHistoryRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public SearchHistoryDto create(SearchHistoryParam param){
        SearchHistory searchHistory = new SearchHistory();
        BeanUtils.copyProperties(param, searchHistory);
        searchHistory.setSearchingTime(LocalDateTime.now());
        searchHistory = searchHistoryRepository.save(searchHistory);
        return SearchHistoryDto.builder()
                .searchingKeyword(searchHistory.getSearchingKeyword())
                .searchingTime(searchHistory.getSearchingTime())
                .build();
    }

    public Page<SearchHistoryDto> getAll(Pageable pageable) {
        return searchHistoryRepository.findAll(pageable).map(searchHistory ->
                SearchHistoryDto.builder()
                        .searchingTime(searchHistory.getSearchingTime())
                        .searchingKeyword(searchHistory.getSearchingKeyword())
                        .build()
        );
    }
}
