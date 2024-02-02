package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.LineStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LineStorageRepo extends JpaRepository<LineStorage,String> {
    Optional<LineStorage> findLineStorageByDepartmentStore_IdAndProduct_Id(Long departmentStroreId, String productId);
}
