package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.StockMovementAPI;
import com.traore.stockmanagement.dto.stockMovement.StockMovementDTO;
import com.traore.stockmanagement.service.StockMovementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StockMovementController implements StockMovementAPI {
    private StockMovementService service;
    @Override
    public StockMovementDTO save(StockMovementDTO dto) {
        return service.save(dto);
    }

    @Override
    public void delete(String stockMovementId) {
        service.delete(stockMovementId);
    }
}
