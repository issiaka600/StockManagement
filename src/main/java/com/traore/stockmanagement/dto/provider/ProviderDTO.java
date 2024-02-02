package com.traore.stockmanagement.dto.provider;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProviderDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String photo;
    private String address;
    private UpdateEnterpriseDTO enterprise;
    private List<CommandProviderDTO> commandProviders;
}
