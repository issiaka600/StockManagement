package com.traore.stockmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class LineStorage extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name = "idDepartmentStore")
    private DepartmentStore departmentStore;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;


}
