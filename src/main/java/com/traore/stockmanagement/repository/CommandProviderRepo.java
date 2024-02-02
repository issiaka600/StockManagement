package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.CommandProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandProviderRepo extends JpaRepository<CommandProvider,String> {
}
