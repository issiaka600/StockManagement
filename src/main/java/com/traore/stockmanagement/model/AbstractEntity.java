package com.traore.stockmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class   AbstractEntity {
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Instant creationDate;
    @LastModifiedDate
    private Instant lastModifiedDate;
}
