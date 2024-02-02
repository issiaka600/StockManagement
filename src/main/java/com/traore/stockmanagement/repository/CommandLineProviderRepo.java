package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.CommandLineProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandLineProviderRepo extends JpaRepository<CommandLineProvider,String> {
}
