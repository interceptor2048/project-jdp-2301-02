package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "PRODUCT_GROUP")
public class Group {

    @Id
    @NonNull
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}