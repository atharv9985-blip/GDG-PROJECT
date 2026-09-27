package com.stocksurge.repository;

import com.stocksurge.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByRequirementIdOrderByMatchScoreDesc(Long requirementId);

    void deleteByRequirementId(Long requirementId);
}