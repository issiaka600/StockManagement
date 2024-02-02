package com.traore.stockmanagement.dto.user;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class CreateUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String photo;
    private String address;
    private UpdateEnterpriseDTO enterprise;
}
