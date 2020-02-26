package com.study.library.entities;
import javax.persistence.*;
import java.util.List;

@Entity
public class BookEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private String comment;
    @ManyToMany()
    private List<AuthorEntity> authors;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }
}
