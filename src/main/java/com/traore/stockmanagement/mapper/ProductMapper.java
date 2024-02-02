package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.product.CreateProductDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import com.traore.stockmanagement.dto.product.ProductWithLineStorageDTO;
import com.traore.stockmanagement.dto.product.ProductWithStockMovementDTO;
import com.traore.stockmanagement.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public CreateProductDTO fromEntityToCreateProductDTO(Product product){
        if(product != null){
            return CreateProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .unitPrice(product.getUnitPrice())
                    .category(
                            CategoryMapper.fromEntityStatic(
                                    product.getCategory()
                            )
                    )
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    product.getEnterprise()
                            )
                    )
                    .photo(product.getPhoto())
                    .quantityMin(product.getQuantityMin())
                    .build();
        }
        return null;
    }


    public Product fromCreateProductDTOToEntity(CreateProductDTO dto){
        if(dto != null){
            return Product.builder()
                    .name(dto.getName().trim())
                    .unitPrice(dto.getUnitPrice())
                    .quantityMin(dto.getQuantityMin())
                    .category(
                            CategoryMapper.toEntityStatic(
                                    dto.getCategory()
                            )
                    )
                    .enterprise(
                            EnterpriseMapper.fromUpdateDTOToEntityStatic(
                                    dto.getEnterprise()
                            )
                    )
                    .photo(dto.getPhoto().trim())
                    .build();
        }
        return null;
    }


    public ProductWithLineStorageDTO fromEntityToProductWithLineStorages(Product product){
        if(product != null){
            return ProductWithLineStorageDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .unitPrice(product.getUnitPrice())
                    .category(
                            CategoryMapper.fromEntityStatic(
                                    product.getCategory()
                            )
                    )
                    .photo(product.getPhoto())
                    .quantityMin(product.getQuantityMin())
                    .lineStorages(
                            LineStorageMapper.fromEntityStatic(
                                    product.getLineStorages()
                            )
                    )
                    .build();
        }
        return null;
    }


    public ProductWithStockMovementDTO fromEntityToProductWithStockMvt(Product product){
        if(product != null){
            return ProductWithStockMovementDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .unitPrice(product.getUnitPrice())
                    .category(
                            CategoryMapper.fromEntityStatic(
                                    product.getCategory()
                            )
                    )
                    .photo(product.getPhoto())
                    .quantityMin(product.getQuantityMin())
                    .stockMovements(
                            StockMovementMapper.fromEntityToStockMovementDTOStatic(
                                    product.getStockMovements()
                            )
                    )
                    .build();
        }
        return null;
    }

    public List<ProductDTO> fromEntityToProductDTO(List<Product> products){
        if (products != null){
            List<ProductDTO> dtos = new ArrayList<>();
            for (Product product : products){
                dtos.add(
                        fromEntityStatic(product)
                );
            }
            return dtos;
        }
        return null;
    }

    public static ProductDTO fromEntityStatic(Product product){
        if(product != null){
            return ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .photo(product.getPhoto())
                    .unitPrice(product.getUnitPrice())
                    .quantityMin(product.getQuantityMin())
                    .category(
                            CategoryMapper.fromEntityStatic(
                                    product.getCategory()
                            )
                    )
                    .build();
        }
        return null;
    }
    public static Product fromProductDTOToEntity(ProductDTO dto){
        if(dto != null){
            return Product.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .photo(dto.getPhoto())
                    .unitPrice(dto.getUnitPrice())
                    .quantityMin(dto.getQuantityMin())
                    .category(
                            CategoryMapper.toEntityStatic(
                                    dto.getCategory()
                            )
                    )
                    .build();
        }
        return null;
    }

    public static List<ProductDTO> fromEntityStatic(List<Product> products){
        if (products != null){
            List<ProductDTO> dtos = new ArrayList<>();
            for (Product product : products){
                dtos.add(
                        fromEntityStatic(product)
                );
            }
            return dtos;
        }
        return null;
    }

}
