package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.dto.provider.CreateProviderDTO;
import com.traore.stockmanagement.dto.provider.ProviderDTO;
import com.traore.stockmanagement.enums.ClientStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProviderService {
    ProviderDTO save(CreateProviderDTO dto);
    ProviderDTO save(MultipartFile file, String firstName, String lastName, String email, String phone, String address, ClientStatus status, Long enterpriseId);
    CreateProviderDTO update(CreateProviderDTO dto);
    ProviderDTO getProvider(String providerId);
    List<ProviderDTO> getProviders(Long enterpriseId);
    void delete(String providerId);
}
