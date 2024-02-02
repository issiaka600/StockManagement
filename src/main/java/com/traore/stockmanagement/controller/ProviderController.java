package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.ProviderAPI;
import com.traore.stockmanagement.dto.provider.CreateProviderDTO;
import com.traore.stockmanagement.dto.provider.ProviderDTO;
import com.traore.stockmanagement.enums.ClientStatus;
import com.traore.stockmanagement.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProviderController implements ProviderAPI {
    private ProviderService service;

    @Override
    public ProviderDTO save(CreateProviderDTO dto) {
        return service.save(dto);
    }

    @Override
    public ProviderDTO save(MultipartFile file, String firstName, String lastName, String email, String phone, String address, ClientStatus status, Long enterpriseId) {
        return service.save(file,firstName,lastName,email,phone,address,status,enterpriseId);
    }

    @Override
    public CreateProviderDTO update(CreateProviderDTO dto) {
        return service.update(dto);
    }

    @Override
    public ProviderDTO getProvider(String providerId) {
        return service.getProvider(providerId);
    }

    @Override
    public List<ProviderDTO> getProviders(Long enterpriseId) {
        return service.getProviders(enterpriseId);
    }

    @Override
    public void delete(String providerId) {
        service.delete(providerId);
    }
}
