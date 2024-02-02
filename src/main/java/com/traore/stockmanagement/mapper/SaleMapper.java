package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.sale.SaleDTO;
import com.traore.stockmanagement.dto.sale.SaleOnlyDTO;
import com.traore.stockmanagement.model.Sale;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SaleMapper {
    public static SaleOnlyDTO fromEntityToSaleOnlyDTO(Sale sale){
        if(sale != null){
            return SaleOnlyDTO.builder()
                    .id(sale.getId())
                    .date(sale.getDate())
                    .status(sale.getStatus())
                    .build();
        }
        return null;
    }
    public static Sale fromSaleOnlyDTOToEntity(SaleOnlyDTO dto){
        if(dto != null){
            return Sale.builder()
                    .id(dto.getId())
                    .date(dto.getDate())
                    .status(dto.getStatus())
                    .build();
        }
        return null;
    }

    public SaleDTO fromEntityToSaleDTO(Sale sale){
        if(sale != null){
            return SaleDTO.builder()
                    .id(sale.getId())
                    .date(sale.getDate())
                    .saleLines(
                            SaleLineMapper.fromEntityStatic(
                                    sale.getSaleLines()
                            )
                    )
                    .status(sale.getStatus())
                    .user(
                            UserMapper.FromEntityToCreateUserDTOStatic(
                                    sale.getUser()
                            )
                    )
                    .build();
        }
        return null;
    }

    public List<SaleDTO> fromEntityToSaleDTO(List<Sale> sales){
        if (!sales.isEmpty()){
            List<SaleDTO> dtos = new ArrayList<>();
            for (Sale sale : sales){
                dtos.add(
                        fromEntityToSaleDTO(sale)
                );
            }
            return dtos;
        }
        return null;
    }

    public Sale toEntity(SaleDTO dto){
        if(dto != null){
            return Sale.builder()
                    .date(dto.getDate())
                    .status(dto.getStatus())
                    .user(
                            UserMapper.fromCreateUserDTOtoEntityStatic(
                                    dto.getUser()
                            )
                    )
                    .build();
        }
        return null;
    }
}
