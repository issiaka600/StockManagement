package com.traore.stockmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Provider extends AbstractEntity{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String photo;
    @Transient
    private Attachment attachment;
    private String address;

    @OneToMany(mappedBy ="provider")
    private List<CommandProvider> commandProviders;

    @ManyToOne
    @JoinColumn(name = "idEnterprise")
    private Enterprise enterprise;
}
