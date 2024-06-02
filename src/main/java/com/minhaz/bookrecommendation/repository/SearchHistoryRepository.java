package com.minhaz.bookrecommendation.repository;

import com.minhaz.bookrecommendation.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
}
