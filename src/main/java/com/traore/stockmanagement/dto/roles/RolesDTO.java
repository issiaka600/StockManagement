package com.traore.stockmanagement.dto.roles;

import com.traore.stockmanagement.dto.user.CreateUserDTO;
import com.traore.stockmanagement.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class RolesDTO {
    private Integer id;
    private RoleName name;
    private CreateUserDTO user;
}
