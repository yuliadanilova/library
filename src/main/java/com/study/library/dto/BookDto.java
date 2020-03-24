package com.study.library.dto;

import java.util.List;

public class BookDto {
    private Integer id;
    private String name;
    private Integer count;
    private Integer year;
    private List<AuthorDto> authors;
    private List<RentDto> rents;

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }

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


    public List<RentDto> getRents() {
        return rents;
    }

    public void setRents(List<RentDto> rents) {
        this.rents = rents;
    }

}
