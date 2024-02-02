package com.traore.stockmanagement.dto.provider;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.enums.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CreateProviderDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String photo;
    private String address;
    private ClientStatus status;
    private UpdateEnterpriseDTO enterprise;
}
