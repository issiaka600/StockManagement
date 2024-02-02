package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepo extends JpaRepository<Sale,String> {
    List<Sale> findSalesByUser_Enterprise_IdOrderByDateDesc(Long enterpriseId);
}
