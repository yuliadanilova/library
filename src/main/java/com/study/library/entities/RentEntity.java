package com.study.library.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class RentEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Integer clientId;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate finishDate;
    @Column
    private Integer bookId;
    @Column
    private Integer rating;
    @Column
    private String comment;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
