package com.traore.stockmanagement.dto.enterprise;

import com.traore.stockmanagement.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor @Builder
public class EnterpriseWithUsersDTO {
    private Long id;
    private String name;
    private String logoId;
    List<UserDTO> userDTOS;
}
