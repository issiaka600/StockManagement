package com.traore.stockmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "images")
public class Attachment {
    @Id
    private String id;
    private String fileName;
    private byte[] data;
}
