package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepo extends JpaRepository<Enterprise,Long> {
    boolean existsByNameEqualsIgnoreCase(String name);
}
