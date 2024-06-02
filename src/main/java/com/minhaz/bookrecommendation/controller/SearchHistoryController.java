package com.minhaz.bookrecommendation.controller;

import com.minhaz.bookrecommendation.dto.SearchHistoryDto;
import com.minhaz.bookrecommendation.param.SearchHistoryParam;
import com.minhaz.bookrecommendation.service.SearchHistoryService;
import com.minhaz.bookrecommendation.util.ApiConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.SearchHistory.ROOT)
@RequiredArgsConstructor
public class SearchHistoryController {
    private final SearchHistoryService searchHistoryService;

    @PreAuthorize("admin")
    @PostMapping()
    public ResponseEntity<SearchHistoryDto> create(@RequestBody SearchHistoryParam param) {
        return ResponseEntity.ok(searchHistoryService.create(param));
    }

    @GetMapping()
    public ResponseEntity<Page<SearchHistoryDto>> getAll(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok(searchHistoryService.getAll(PageRequest.of(pageNo, pageSize)));
    }


}
