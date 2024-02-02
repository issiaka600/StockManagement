package com.traore.stockmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class DepartmentStore extends AbstractEntity{
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "idEnterprise")
    private Enterprise enterprise;

    @OneToMany(mappedBy = "departmentStore")
    private List<LineStorage> lineStorages;

    @OneToMany(mappedBy = "departmentStore")
    private List<StockMovement> stockMovements;

}
