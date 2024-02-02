package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findUsersByEnterprise_Id(Long enterprise);
}
