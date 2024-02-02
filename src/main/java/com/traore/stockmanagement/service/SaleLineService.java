package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.saleLine.SaleLineDTO;

import java.util.List;

public interface SaleLineService {
    SaleLineDTO save(SaleLineDTO dto);
    List<SaleLineDTO> save(List<SaleLineDTO> dtos);
    SaleLineDTO update(SaleLineDTO dto);
    void delete(String saleLineId);
}
