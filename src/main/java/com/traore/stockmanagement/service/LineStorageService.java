package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.lineStorage.LineStorageDTO;

import java.math.BigDecimal;
import java.util.List;

public interface LineStorageService {
    LineStorageDTO save(LineStorageDTO dto);
    LineStorageDTO update(String id, BigDecimal quantity);
    LineStorageDTO getLineStorage(String productId,Long departmentStore);
    void delete(String lineStorageId);

}
