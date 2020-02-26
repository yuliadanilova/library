package com.study.library.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class AuthorEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

}
