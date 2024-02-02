package com.traore.stockmanagement.service;

import com.traore.stockmanagement.model.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    String saveImage(MultipartFile file);
    byte[] getImage(String imageId);

    Attachment getAttachment(String imageId);
}
