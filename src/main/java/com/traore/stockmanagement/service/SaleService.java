package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.sale.SaleDTO;
import com.traore.stockmanagement.dto.sale.SaleOnlyDTO;

import java.util.List;

public interface SaleService {
    SaleDTO save(SaleDTO dto);
    SaleDTO update(SaleOnlyDTO dto);

    SaleDTO getSale(String saleId);
    List<SaleDTO> getSales(Long enterpriseId);
    void delete(String saleId);
}
