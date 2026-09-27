package com.stocksurge.repository;
import com.stocksurge.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findByCategoryIgnoreCase(String category);
}