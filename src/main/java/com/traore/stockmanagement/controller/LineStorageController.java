package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.LineStorageAPI;
import com.traore.stockmanagement.dto.lineStorage.LineStorageDTO;
import com.traore.stockmanagement.service.LineStorageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class LineStorageController implements LineStorageAPI {
    private LineStorageService service;
    @Override
    public LineStorageDTO update(String id, BigDecimal quantity) {
        return service.update(id,quantity);
    }

    @Override
    public LineStorageDTO getLineStorage(String productId, Long departmentStoreId) {
        return service.getLineStorage(productId, departmentStoreId);
    }

    @Override
    public void delete(String id) {
        service.delete(id);
    }
}
