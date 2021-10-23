package com.kodluyoruz.weekfive.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 120)
    private String title;

    @Builder.Default
    private Boolean available = true;

    @Column(name = "author_id")
    private Integer authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false, insertable = false, updatable = false)
    private Author author;


}
