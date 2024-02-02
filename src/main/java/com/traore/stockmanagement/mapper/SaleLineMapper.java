package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.saleLine.SaleLineDTO;
import com.traore.stockmanagement.model.SaleLine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SaleLineMapper {
    public SaleLineDTO fromEntity(SaleLine saleLine){
        if (saleLine != null){
             return SaleLineDTO.builder()
                     .id(saleLine.getId())
                     .unitPrice(saleLine.getUnitPrice())
                     .quantity(saleLine.getQuantity())
                     .product(
                             ProductMapper.fromEntityStatic(
                                     saleLine.getProduct()
                             )
                     )
                     .sale(
                             SaleMapper.fromEntityToSaleOnlyDTO(
                                     saleLine.getSale()
                             )
                     )
                     .build();
        }
        return null;
    }
    public SaleLine toEntity(SaleLineDTO dto){
        if (dto != null){
            return SaleLine.builder()
                    .unitPrice(dto.getUnitPrice())
                    .quantity(dto.getQuantity())
                    .product(
                            ProductMapper.fromProductDTOToEntity(
                                    dto.getProduct()
                            )
                    )
                    .sale(
                            SaleMapper.fromSaleOnlyDTOToEntity(
                                    dto.getSale()
                            )
                    )
                    .build();
        }
        return null;
    }



    public static SaleLineDTO fromEntityStatic(SaleLine saleLine){
        if (saleLine != null){
            return SaleLineDTO.builder()
                    .id(saleLine.getId())
                    .unitPrice(saleLine.getUnitPrice())
                    .quantity(saleLine.getQuantity())
                    .product(
                            ProductMapper.fromEntityStatic(
                                    saleLine.getProduct()
                            )
                    )
                    .build();
        }
        return null;
    }

    public static List<SaleLineDTO> fromEntityStatic(List<SaleLine> saleLines){
        if (saleLines != null){
           List<SaleLineDTO> dtos = new ArrayList<>();
           for (SaleLine saleLine : saleLines){
               dtos.add(
                       fromEntityStatic(saleLine)
               );
           }
           return dtos;
        }
        return null;
    }

    public static SaleLine toEntityStatic(SaleLineDTO dto){
        if (dto != null){
            return SaleLine.builder()
                    .id(dto.getId())
                    .unitPrice(dto.getUnitPrice())
                    .quantity(dto.getQuantity())
                    .product(
                            ProductMapper.fromProductDTOToEntity(
                                    dto.getProduct()
                            )
                    )
                    .build();
        }
        return null;
    }
}
