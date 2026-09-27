package com.stocksurge.service;

import com.stocksurge.entity.*;
import com.stocksurge.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {

    private final InventoryRepository inventoryRepository;
    private final MatchRepository matchRepository;
    private final RequirementService requirementService;

    public MatchService(
            InventoryRepository inventoryRepository,
            MatchRepository matchRepository,
            RequirementService requirementService) {

        this.inventoryRepository = inventoryRepository;
        this.matchRepository = matchRepository;
        this.requirementService = requirementService;
    }

    public List<Match> generateMatches(Long requirementId) {

        Requirement req = requirementService.getById(requirementId);

        matchRepository.deleteByRequirementId(requirementId);

        List<Inventory> items =
                inventoryRepository.findByCategoryIgnoreCase(req.getCategory());

        List<Match> results = new ArrayList<>();

        for (Inventory item : items) {

            if (item.getStatus() != null &&
                    item.getStatus() == InventoryStatus.INACTIVE) {
                continue;
            }

            int score = 20;
            List<String> reasons = new ArrayList<>();

            reasons.add("Category matches");

            if (item.getQuantity() != null &&
                    item.getQuantity() >= req.getQuantityRequired()) {
                score += 25;
                reasons.add("Enough quantity available");
            }

            if (item.getPricePerUnit() != null &&
                    req.getMaxBudgetPerUnit() != null &&
                    item.getPricePerUnit() <= req.getMaxBudgetPerUnit()) {
                score += 25;
                reasons.add("Within buyer budget");
            }

            if (item.getLocation() != null &&
                    req.getLocation() != null &&
                    item.getLocation().equalsIgnoreCase(req.getLocation())) {
                score += 20;
                reasons.add("Same location");
            }

            if (item.getConditionStatus() != null &&
                    !item.getConditionStatus().isBlank()) {
                score += 10;
                reasons.add("Condition information available");
            }

            results.add(
                    Match.builder()
                            .inventory(item)
                            .requirement(req)
                            .matchScore(Math.min(score, 100))
                            .reason(String.join("; ", reasons))
                            .build()
            );
        }

        return matchRepository.saveAll(results);
    }

    public List<Match> getMatches(Long requirementId) {

        return matchRepository
                .findByRequirementIdOrderByMatchScoreDesc(requirementId);
    }
}