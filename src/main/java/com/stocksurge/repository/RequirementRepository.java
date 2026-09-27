package com.stocksurge.repository;
import com.stocksurge.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RequirementRepository extends JpaRepository<Requirement,Long> {}