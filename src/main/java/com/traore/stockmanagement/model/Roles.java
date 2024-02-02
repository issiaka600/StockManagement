package com.traore.stockmanagement.model;

import com.traore.stockmanagement.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Roles extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

}
