package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

}
