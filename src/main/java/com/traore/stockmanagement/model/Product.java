package com.traore.stockmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Product extends AbstractEntity{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal quantityMin;
    private String photo;
    @Transient
    private Attachment attachment;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "idEnterprise")
    private Enterprise enterprise;

    @OneToMany(mappedBy ="product")
    private List<StockMovement> stockMovements;

    @OneToMany(mappedBy ="product")
    private List<LineStorage> lineStorages;

    @OneToMany(mappedBy ="product")
    private List<CommandLineProvider> commandLineProviders;

    @OneToMany(mappedBy ="product")
    private List<CommandLineClient> commandLineClients;

    @OneToMany(mappedBy ="product")
    private List<SaleLine> saleLines;


}
