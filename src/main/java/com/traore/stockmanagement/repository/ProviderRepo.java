package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderRepo extends JpaRepository<Provider,String> {
    List<Provider> findProvidersByEnterprise_Id(Long enterpriseId);
}
