package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.SaleLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleLineRepo extends JpaRepository<SaleLine,String> {
}
