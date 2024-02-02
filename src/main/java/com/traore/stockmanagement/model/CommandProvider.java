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
public class CommandProvider extends AbstractEntity{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private CommandStatus status;


    @OneToMany(mappedBy ="commandProvider",cascade = CascadeType.ALL)
    private List<CommandLineProvider> commandLineProviders;

    @ManyToOne
    @JoinColumn(name = "idProvider")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

}
