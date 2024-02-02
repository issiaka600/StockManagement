package com.traore.stockmanagement.model;

import com.traore.stockmanagement.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Client extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @OneToMany(mappedBy ="client")
    private List<CommandClient> commandClients;

    @ManyToOne
    @JoinColumn(name = "idEnterprise")
    private Enterprise enterprise;
}
