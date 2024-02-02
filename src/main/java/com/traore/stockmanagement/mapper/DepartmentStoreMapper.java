package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreOnlyDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithLineStoragesDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithStockMovementDTO;
import com.traore.stockmanagement.model.DepartmentStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentStoreMapper {

    public DepartmentStoreDTO fromEntity(DepartmentStore departmentStore){
        if (departmentStore!=null){
            return DepartmentStoreDTO.builder()
                    .id(departmentStore.getId())
                    .name(departmentStore.getName())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    departmentStore.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }

    public DepartmentStore toEntity(DepartmentStoreDTO dto){
        if (dto!=null){
            return DepartmentStore.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .enterprise(
                            EnterpriseMapper.fromUpdateDTOToEntityStatic(
                                    dto.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }

    public List<DepartmentStoreDTO> fromEntityStatic(List<DepartmentStore> departmentStores){
        if (departmentStores != null){
            List<DepartmentStoreDTO> dtos = new ArrayList<>();
            for (DepartmentStore departmentStore : departmentStores){
                dtos.add(
                        fromEntity(
                                departmentStore
                        )
                );
            }
            return dtos;
        }
        return null;
    }
    public static DepartmentStoreOnlyDTO fromEntityToDepartmentStoreOnlyDTO(DepartmentStore departmentStore){
        return DepartmentStoreOnlyDTO.builder()
                .id(departmentStore.getId())
                .name(departmentStore.getName())
                .build();
    }

    public static DepartmentStore fromDepartmentStoreOnlyDTOToEntity(DepartmentStoreOnlyDTO dto){
        return DepartmentStore.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public static List<DepartmentStoreOnlyDTO> fromEntityToDepartmentStoreOnlyDTO(List<DepartmentStore> departmentStores){
        if (departmentStores!=null){
            List<DepartmentStoreOnlyDTO> dtos = new ArrayList<>();
            for (DepartmentStore departmentStore : departmentStores){
                dtos.add(
                        fromEntityToDepartmentStoreOnlyDTO(departmentStore)
                );
            }
            return dtos;
        }
        return null;
    }

    public DepartmentStoreWithLineStoragesDTO fromEntityToDepartmentStoreDetails(DepartmentStore departmentStore){
        if (departmentStore != null){
            return DepartmentStoreWithLineStoragesDTO.builder()
                    .id(departmentStore.getId())
                    .name(departmentStore.getName())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    departmentStore.getEnterprise()
                            )
                    )
                    .lineStorages(
                            LineStorageMapper.fromEntityStatic(
                                    departmentStore.getLineStorages()
                            )
                    )
                    .build();
        }
        return null;
    }

    public DepartmentStoreWithStockMovementDTO fromEntityToDepartmentStoreWithStockMvt(DepartmentStore departmentStore){
        if (departmentStore != null){
            return DepartmentStoreWithStockMovementDTO.builder()
                    .id(departmentStore.getId())
                    .name(departmentStore.getName())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    departmentStore.getEnterprise()
                            )
                    )
                    .stockMovements(
                            StockMovementMapper.fromEntityToStockMovementDTOStatic(
                                    departmentStore.getStockMovements()
                            )
                    )
                    .build();
        }
        return null;
    }
}
