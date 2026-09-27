package com.stocksurge.service;

import com.stocksurge.entity.Inventory;
import com.stocksurge.entity.User;
import com.stocksurge.repository.InventoryRepository;
import com.stocksurge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;
    private final UserRepository userRepository;

    public InventoryService(InventoryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Inventory create(Inventory x, Long sellerId) {

        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found: " + sellerId));

        x.setSeller(seller);

        return repository.save(x);
    }

    public List<Inventory> getAll() {
        return repository.findAll();
    }

    public Inventory getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found: " + id));
    }

    public Inventory update(Long id, Inventory x) {

        Inventory e = getById(id);

        e.setProductName(x.getProductName());
        e.setCategory(x.getCategory());
        e.setDescription(x.getDescription());
        e.setQuantity(x.getQuantity());
        e.setConditionStatus(x.getConditionStatus());
        e.setPricePerUnit(x.getPricePerUnit());
        e.setLocation(x.getLocation());
        e.setImageUrl(x.getImageUrl());
        e.setStatus(x.getStatus());

        return repository.save(e);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}