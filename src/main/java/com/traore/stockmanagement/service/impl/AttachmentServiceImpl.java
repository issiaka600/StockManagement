package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.model.Attachment;
import com.traore.stockmanagement.repository.AttachmentRepo;
import com.traore.stockmanagement.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private AttachmentRepo attachmentRepo;

    @Override
    public String saveImage(MultipartFile file) {
        try {
            Attachment attachment = Attachment.builder()
                    .fileName(file.getOriginalFilename())
                    .data(file.getBytes())
                    .build();

            Attachment savedImage = attachmentRepo.save(attachment);
            return savedImage.getId();
        } catch (Exception e) {
            throw new InvalidOperationException("Failed to save image", ErrorCodes.UPLOADING_PHOTO_EXCEPTION);
        }
    }

    @Override
    public byte[] getImage(String imageId) {
        if (imageId == null || imageId.isBlank()){
            throw new InvalidEntityException("Image ID not valid", ErrorCodes.GETTING_PHOTO_EXCEPTION);
        }

        return attachmentRepo.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Image with ID {%s} not found", imageId), ErrorCodes.GETTING_PHOTO_EXCEPTION))
                .getData();
    }

    @Override
    public Attachment getAttachment(String imageId) {
        if (imageId == null || imageId.isBlank()){
            throw new InvalidEntityException("Image ID not valid", ErrorCodes.GETTING_PHOTO_EXCEPTION);
        }

        return attachmentRepo.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Image with ID {%s} not found", imageId),
                        ErrorCodes.GETTING_PHOTO_EXCEPTION));
    }
}
