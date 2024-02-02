package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.UserAPI;
import com.traore.stockmanagement.dto.user.CreateUserDTO;
import com.traore.stockmanagement.dto.user.UpdateUserPasswordDTO;
import com.traore.stockmanagement.dto.user.UserDTO;
import com.traore.stockmanagement.enums.RoleName;
import com.traore.stockmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {
    private UserService userService;

    @Override
    public CreateUserDTO save(CreateUserDTO userDTO) {
        return userService.save(userDTO);
    }

    @Override
    public CreateUserDTO save(MultipartFile file, String firstName, String lastName, String email, String password, String phone, String address, Long enterpriseId, RoleName role) {
        return userService.save(file, firstName, lastName, email, password, phone, address, enterpriseId, role);
    }

    @Override
    public UserDTO getUser(Long userId) {
        return userService.getUser(userId);
    }

    @Override
    public UserDTO updateUserPassword(UpdateUserPasswordDTO dto) {
        return userService.updateUserPassword(dto);
    }

    @Override
    public void delete(Long userId) {
        userService.delete(userId);
    }
}
