package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.stockMovement.StockMovementDTO;
import com.traore.stockmanagement.model.StockMovement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockMovementMapper {
    public StockMovementDTO fromEntity(StockMovement stockMovement){
        if(stockMovement != null){
            return StockMovementDTO.builder()
                    .id(stockMovement.getId())
                    .date(stockMovement.getDate())
                    .quantity(stockMovement.getQuantity())
                    .type(stockMovement.getType())
                    .expiryDate(stockMovement.getExpiryDate())
                    .unitPrice(stockMovement.getUnitPrice())
                    .departmentStore(
                            DepartmentStoreMapper.fromEntityToDepartmentStoreOnlyDTO(
                                    stockMovement.getDepartmentStore()
                            )
                    )
                    .product(
                            ProductMapper.fromEntityStatic(
                                    stockMovement.getProduct()
                            )
                    )
                    .build();
        }
        return null;
    }
    public StockMovement toEntity(StockMovementDTO dto){
        if(dto != null){
            return StockMovement.builder()
                    .date(dto.getDate())
                    .quantity(dto.getQuantity())
                    .type(dto.getType())
                    .expiryDate(dto.getExpiryDate())
                    .unitPrice(dto.getUnitPrice())
                    .departmentStore(
                            DepartmentStoreMapper.fromDepartmentStoreOnlyDTOToEntity(
                                    dto.getDepartmentStore()
                            )
                    )
                    .product(
                            ProductMapper.fromProductDTOToEntity(
                                    dto.getProduct()
                            )
                    )
                    .build();
        }
        return null;
    }

    public List<StockMovementDTO> fromEntity(List<StockMovement> stockMovements){
        if(stockMovements != null){
            List<StockMovementDTO> dtos = new ArrayList<>();
            for (StockMovement stockMovement : stockMovements){
                dtos.add(
                        fromEntity(stockMovement)
                );
            }
            return dtos;
        }
        return null;
    }


    public static StockMovementDTO fromEntityToStockMovementDTOStatic(StockMovement stockMovement){
        if(stockMovement != null){
            return StockMovementDTO.builder()
                    .id(stockMovement.getId())
                    .date(stockMovement.getDate())
                    .quantity(stockMovement.getQuantity())
                    .type(stockMovement.getType())
                    .expiryDate(stockMovement.getExpiryDate())
                    .unitPrice(stockMovement.getUnitPrice())
                    .departmentStore(
                            DepartmentStoreMapper.fromEntityToDepartmentStoreOnlyDTO(
                                    stockMovement.getDepartmentStore()
                            )
                    )
                    .product(
                            ProductMapper.fromEntityStatic(
                                    stockMovement.getProduct()
                            )
                    )
                    .build();
        }
        return null;
    }

    public static List<StockMovementDTO> fromEntityToStockMovementDTOStatic(List<StockMovement> stockMovements){
        if(stockMovements != null){
            List<StockMovementDTO> dtos = new ArrayList<>();
            for (StockMovement stockMovement : stockMovements){
                dtos.add(
                        fromEntityToStockMovementDTOStatic(stockMovement)
                );
            }
            return dtos;
        }
        return null;
    }
}
