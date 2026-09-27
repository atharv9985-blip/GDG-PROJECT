package com.stocksurge.controller;

import com.stocksurge.entity.Match;
import com.stocksurge.entity.Requirement;
import com.stocksurge.service.MatchService;
import com.stocksurge.service.RequirementService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/requirements")
@CrossOrigin(origins = "*")
public class RequirementController {

    private final RequirementService service;
    private final MatchService matchService;

    public RequirementController(
            RequirementService service,
            MatchService matchService) {

        this.service = service;
        this.matchService = matchService;
    }

    @PostMapping
    public Requirement create(
            @RequestParam Long buyerId,
            @Valid @RequestBody Requirement x) {

        return service.create(x, buyerId);
    }

    @GetMapping
    public List<Requirement> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Requirement get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/{id}/matches")
    public List<Match> generateMatches(@PathVariable Long id) {
        return matchService.generateMatches(id);

    }
    @GetMapping("/{id}/matches")
    public List<Match> getMatches(@PathVariable Long id) {
        return matchService.getMatches(id);
    }
}