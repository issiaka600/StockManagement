package com.traore.stockmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class CommandLineProvider extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal unitPrice;
    private BigDecimal quantity;


    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idCommandProvider")
    private CommandProvider commandProvider;

}
