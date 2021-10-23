package com.kodluyoruz.weekfive.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Book> books;
}
