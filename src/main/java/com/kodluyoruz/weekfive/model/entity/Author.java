package com.kodluyoruz.weekfive.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Book> books;
}
