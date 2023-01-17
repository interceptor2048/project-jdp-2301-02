package com.kodilla.ecommercee.domain;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUCT_GROUPS")
public class Group {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(targetEntity = Product.class, mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> productList = new ArrayList<>();

    public Group(String name, String desc) {
        this.name = name;
    }

}

