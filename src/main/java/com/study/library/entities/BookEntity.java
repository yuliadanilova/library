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
    private Integer count;
    @Column
    private Integer year;
    @ManyToMany()
    private List<AuthorEntity> authors;
    @OneToMany( mappedBy = "book" )
    private List<RentEntity> rents;

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

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<RentEntity> getRents() {
        return rents;
    }

    public void setRents(List<RentEntity> rents) {
        this.rents = rents;
    }
}
