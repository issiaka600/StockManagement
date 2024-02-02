package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.DepartmentStoreAPI;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithLineStoragesDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithStockMovementDTO;
import com.traore.stockmanagement.service.DepartmentStoreService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@AllArgsConstructor
public class DepartmentStoreController implements DepartmentStoreAPI {
    private DepartmentStoreService service;

    @Override
    public DepartmentStoreDTO save(DepartmentStoreDTO dto) {
        return service.save(dto);
    }

    @Override
    public DepartmentStoreDTO update(DepartmentStoreDTO dto) {
        return service.update(dto);
    }

    @Override
    public DepartmentStoreWithLineStoragesDTO getDepartmentStoreWithLineStorages(Long departmentStoreId) {
        return service.getDepartmentStoreWithLineStorages(departmentStoreId);
    }

    @Override
    public DepartmentStoreWithStockMovementDTO getDepartmentStoreWithStockMovements(Long departmentStoreId) {
        return service.getDepartmentStoreWithStockMovements(departmentStoreId);
    }

    @Override
    public void delete(Long departmentStoreId) {
        service.delete(departmentStoreId);
    }
}
