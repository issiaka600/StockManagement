package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/stock_management/images")
@AllArgsConstructor
public class AttachmentController {
    private AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String imageId = attachmentService.saveImage(file);
        return ResponseEntity.ok(imageId);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageId) {
        byte[] imageData = attachmentService.getImage(imageId);
        return ResponseEntity.ok(imageData);
    }
}
