package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.user.CreateUserDTO;
import com.traore.stockmanagement.dto.user.UpdateUserPasswordDTO;
import com.traore.stockmanagement.dto.user.UserDTO;
import com.traore.stockmanagement.enums.RoleName;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    CreateUserDTO save(CreateUserDTO userDTO);
    CreateUserDTO save(MultipartFile file, String firstName, String lastName, String email, String password, String phone, String address, Long enterpriseId, RoleName role);
   // CreateUserDTO update(CreateUserDTO userDTO);
    UserDTO getUser(Long userId);
    UserDTO updateUserPassword(UpdateUserPasswordDTO dto);
    List<UserDTO> getUsers(Long enterpriseId);
    void delete(Long userId);
}
