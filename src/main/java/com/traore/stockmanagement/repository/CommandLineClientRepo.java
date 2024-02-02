package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.CommandLineClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandLineClientRepo extends JpaRepository<CommandLineClient,String> {
}
