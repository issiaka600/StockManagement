package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.lineStorage.LineStorageDTO;
import com.traore.stockmanagement.model.LineStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LineStorageMapper {
    public LineStorageDTO fromEntity(LineStorage lineStorage){
        if(lineStorage != null){
            return LineStorageDTO.builder()
                    .id(lineStorage.getId())
                    .quantity(lineStorage.getQuantity())
                    .departmentStore(
                            DepartmentStoreMapper.fromEntityToDepartmentStoreOnlyDTO(
                                    lineStorage.getDepartmentStore()
                            )
                    )
                    .product(
                            ProductMapper.fromEntityStatic(
                                    lineStorage.getProduct()
                            )
                    )
                    .build();
        }
        return null;
    }
    public List<LineStorageDTO> fromEntity(List<LineStorage> lineStorages){
        if(lineStorages != null){
            List<LineStorageDTO> dtos = new ArrayList<>();
            for (LineStorage lineStorage : lineStorages){
                dtos.add(
                        fromEntity(lineStorage)
                );
            }
            return dtos;
        }
        return null;
    }

    public static LineStorageDTO fromEntityStatic(LineStorage lineStorage){
        if(lineStorage != null){
            return LineStorageDTO.builder()
                    .id(lineStorage.getId())
                    .quantity(lineStorage.getQuantity())
                    .departmentStore(
                            DepartmentStoreMapper.fromEntityToDepartmentStoreOnlyDTO(
                                    lineStorage.getDepartmentStore()
                            )
                    )
                    .product(
                            ProductMapper.fromEntityStatic(
                                    lineStorage.getProduct()
                            )
                    )
                    .build();
        }
        return null;
    }
    public static List<LineStorageDTO> fromEntityStatic(List<LineStorage> lineStorages){
        if(lineStorages != null){
            List<LineStorageDTO> dtos = new ArrayList<>();
            for (LineStorage lineStorage : lineStorages){
                dtos.add(
                        fromEntityStatic(lineStorage)
                );
            }
            return dtos;
        }
        return null;
    }

    public LineStorage toEntity(LineStorageDTO dto){
        if(dto != null){
            return LineStorage.builder()
                    .quantity(dto.getQuantity())
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

}
