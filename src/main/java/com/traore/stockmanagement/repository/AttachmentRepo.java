package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttachmentRepo extends MongoRepository<Attachment, String> {
}
