package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepo extends JpaRepository<StockMovement,String> {
}
