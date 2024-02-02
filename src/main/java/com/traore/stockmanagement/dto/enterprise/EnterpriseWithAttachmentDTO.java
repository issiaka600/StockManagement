package com.traore.stockmanagement.dto.enterprise;

import com.traore.stockmanagement.model.Attachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class EnterpriseWithAttachmentDTO {
    private Long id;
    private String name;
    private String logoId;
    private Attachment attachment;
}
