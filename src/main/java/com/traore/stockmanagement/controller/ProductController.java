package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.ProductAPI;
import com.traore.stockmanagement.dto.product.CreateProductDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import com.traore.stockmanagement.dto.product.ProductWithLineStorageDTO;
import com.traore.stockmanagement.dto.product.ProductWithStockMovementDTO;
import com.traore.stockmanagement.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController implements ProductAPI {
    private ProductService service;

    @Override
    public CreateProductDTO save(MultipartFile file, String name, BigDecimal unitPrice, BigDecimal quantityMin, Long categoryId, Long enterpriseId) {
        return service.save(file, name, unitPrice, quantityMin, categoryId, enterpriseId);
    }

    @Override
    public CreateProductDTO save(CreateProductDTO dto) {
        return service.save(dto);
    }

    @Override
    public CreateProductDTO update(ProductDTO dto) {
        return service.update(dto);
    }

    @Override
    public CreateProductDTO getProduct(String productId) {
        return service.getProduct(productId);
    }

    @Override
    public ProductWithLineStorageDTO getProductWithLineStorages(String productId) {
        return service.getProductWithLineStorages(productId);
    }

    @Override
    public ProductWithStockMovementDTO getProductWithStockMovements(String productId) {
        return service.getProductWithStockMovements(productId);
    }

    @Override
    public List<ProductDTO> getProducts(Long categoryId) {
        return service.getProducts(categoryId);
    }

    @Override
    public void delete(String productId) {
        service.delete(productId);
    }
}
