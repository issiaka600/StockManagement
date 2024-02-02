package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.SaleLineAPI;
import com.traore.stockmanagement.dto.saleLine.SaleLineDTO;
import com.traore.stockmanagement.service.SaleLineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SaleLineController implements SaleLineAPI {
    private SaleLineService service;

    @Override
    public SaleLineDTO save(SaleLineDTO dto) {
        return service.save(dto);
    }

    @Override
    public SaleLineDTO update(SaleLineDTO dto) {
        return service.update(dto);
    }

    @Override
    public void delete(String saleLineId) {
        service.delete(saleLineId);
    }
}
