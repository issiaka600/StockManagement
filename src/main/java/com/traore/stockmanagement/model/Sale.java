package com.traore.stockmanagement.model;

import com.traore.stockmanagement.enums.CommandStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Sale extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private CommandStatus status;

    @OneToMany(mappedBy ="sale",cascade = CascadeType.ALL)
    private List<SaleLine> saleLines;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

}
