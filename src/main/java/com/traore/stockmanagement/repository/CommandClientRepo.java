package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.CommandClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandClientRepo extends JpaRepository<CommandClient,String> {
}
