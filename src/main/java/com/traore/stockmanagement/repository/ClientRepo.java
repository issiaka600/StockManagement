package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client,String> {
    List<Client> findClientsByEnterprise_Id(Long enterpriseId);
}
