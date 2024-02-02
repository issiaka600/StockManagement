package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.product.CreateProductDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import com.traore.stockmanagement.dto.product.ProductWithLineStorageDTO;
import com.traore.stockmanagement.dto.product.ProductWithStockMovementDTO;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    CreateProductDTO save(CreateProductDTO dto);
    CreateProductDTO save(MultipartFile file, String name, BigDecimal unitPrice, BigDecimal quantityMin, Long categoryId, Long enterpriseId);
    CreateProductDTO update(ProductDTO dto);
    CreateProductDTO getProduct(String productId);
    ProductWithLineStorageDTO getProductWithLineStorages(String productId);
    ProductWithStockMovementDTO getProductWithStockMovements(String productId);
    List<ProductDTO> getProducts(Long categoryId);


    void delete(String productId);
}
