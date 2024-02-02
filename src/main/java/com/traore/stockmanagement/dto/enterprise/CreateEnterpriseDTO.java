package com.traore.stockmanagement.dto.enterprise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data @AllArgsConstructor @Builder
public class CreateEnterpriseDTO {
    private String name;
    private String logoId;
    private MultipartFile logo;
}
