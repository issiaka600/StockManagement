package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithLineStoragesDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithStockMovementDTO;

public interface DepartmentStoreService {
    DepartmentStoreDTO save(DepartmentStoreDTO dto);
    DepartmentStoreDTO update(DepartmentStoreDTO dto);
    DepartmentStoreWithLineStoragesDTO getDepartmentStoreWithLineStorages(Long departmentStoreId);
    DepartmentStoreWithStockMovementDTO getDepartmentStoreWithStockMovements(Long departmentId);
    void delete(Long departmentStoreId);
}
