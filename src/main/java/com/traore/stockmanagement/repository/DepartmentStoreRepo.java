package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.DepartmentStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentStoreRepo extends JpaRepository<DepartmentStore,Long> {
}
