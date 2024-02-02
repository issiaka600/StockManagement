package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.SaleAPI;
import com.traore.stockmanagement.dto.sale.SaleDTO;
import com.traore.stockmanagement.dto.sale.SaleOnlyDTO;
import com.traore.stockmanagement.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SaleController implements SaleAPI {
    private SaleService service;

    @Override
    public SaleDTO save(SaleDTO dto) {
        return service.save(dto);
    }

    @Override
    public SaleDTO update(SaleOnlyDTO dto) {
        return service.update(dto);
    }

    @Override
    public SaleDTO getSale(String saleId) {
        return service.getSale(saleId);
    }

    @Override
    public List<SaleDTO> getSales(Long enterpriseId) {
        return service.getSales(enterpriseId);
    }

    @Override
    public void delete(String saleId) {
        service.delete(saleId);
    }
}
