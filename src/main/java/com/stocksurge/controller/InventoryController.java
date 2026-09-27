package com.stocksurge.controller;

import com.stocksurge.entity.Inventory;
import com.stocksurge.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping
    public Inventory create(
            @RequestParam Long sellerId,
            @Valid @RequestBody Inventory x) {

        return service.create(x, sellerId);
    }

    @GetMapping
    public List<Inventory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Inventory get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Inventory update(
            @PathVariable Long id,
            @RequestBody Inventory x) {

        return service.update(id, x);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}