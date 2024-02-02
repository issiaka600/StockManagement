package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.EnterpriseAPI;
import com.traore.stockmanagement.dto.enterprise.*;
import com.traore.stockmanagement.service.EnterpriseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
public class EnterpriseController implements EnterpriseAPI {
    private EnterpriseService enterpriseService;

    @Override
    public EnterpriseWithAttachmentDTO save(MultipartFile file, String name) {
        return enterpriseService.save(file, name);
    }

    @Override
    public UpdateEnterpriseDTO update(UpdateEnterpriseDTO dto) {
        return enterpriseService.update(dto);
    }

    @Override
    public List<EnterpriseWithAttachmentDTO> getEnterprisesOnly() {
        return enterpriseService.getEnterprisesOnly();
    }

    @Override
    public EnterpriseWithUsersDTO getEnterpriseAndUsers(Long enterpriseId) {
        return enterpriseService.getEnterpriseAndUsers(enterpriseId);
    }

    @Override
    public EnterpriseWithDepartmentStoreDTO getEnterpriseAndDepartmentStores(Long enterpriseId) {
        return enterpriseService.getEnterpriseAndDepartmentStores(enterpriseId);
    }

    @Override
    public EnterpriseWithCategories getEnterpriseWithCategories(Long enterpriseId) {
        return enterpriseService.getEnterpriseWithCategories(enterpriseId);
    }

    @Override
    public EnterpriseWithProductsDTO getEnterpriseWithProducts(Long enterpriseId) {
        return enterpriseService.getEnterpriseWithProducts(enterpriseId);
    }
}
