package com.traore.stockmanagement.model;

import com.traore.stockmanagement.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class StockMovement extends AbstractEntity{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal quantity;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private BigDecimal unitPrice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @Enumerated(EnumType.STRING)
    private MovementType type;

    @ManyToOne
    @JoinColumn(name = "idDepartmentStore")
    private DepartmentStore departmentStore;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

}
