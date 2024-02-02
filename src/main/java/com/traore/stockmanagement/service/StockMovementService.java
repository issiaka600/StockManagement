package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.stockMovement.StockMovementDTO;

import java.util.List;

public interface StockMovementService {
    StockMovementDTO save(StockMovementDTO dto);
    void delete(String stockMovementId);
}
