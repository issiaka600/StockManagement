package com.traore.stockmanagement.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class UpdateUserPasswordDTO {
    private Long id;
    private String email;
    private String password;
}
