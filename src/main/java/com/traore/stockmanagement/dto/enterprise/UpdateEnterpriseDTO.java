package com.traore.stockmanagement.dto.enterprise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data @AllArgsConstructor @Builder
public class UpdateEnterpriseDTO {
    private Long id;
    private String name;
    private String logoId;
}
