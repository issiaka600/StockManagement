package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.enterprise.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnterpriseService {
    EnterpriseWithAttachmentDTO save(MultipartFile file, String name);
    EnterpriseWithAttachmentDTO save(CreateEnterpriseDTO dto);
    UpdateEnterpriseDTO update(UpdateEnterpriseDTO dto);
    List<EnterpriseWithAttachmentDTO> getEnterprisesOnly();
    EnterpriseWithUsersDTO getEnterpriseAndUsers(Long enterpriseId);
    EnterpriseWithDepartmentStoreDTO getEnterpriseAndDepartmentStores(Long enterpriseId);
    EnterpriseWithCategories getEnterpriseWithCategories(Long enterpriseId);
    EnterpriseWithProductsDTO getEnterpriseWithProducts(Long enterpriseId);


}
