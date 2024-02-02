package com.traore.stockmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Enterprise extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String logoId;
    @Transient
    private Attachment logo;

    @OneToMany(mappedBy = "enterprise")
    private List<User> users;

    @OneToMany(mappedBy = "enterprise")
    private List<DepartmentStore> departmentStores;

    @OneToMany(mappedBy = "enterprise")
    private List<Product> products;

    @OneToMany(mappedBy = "enterprise")
    private List<Category> categories;

    @OneToMany(mappedBy = "enterprise")
    private List<Provider> providers;

    @OneToMany(mappedBy = "enterprise")
    private List<Client> clients;

}
