package com.study.library.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class RentEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private ClientEntity client;
    @Column
    private LocalDate deadlineDate;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate finishDate;
    @ManyToOne
    private BookEntity book;
    @Column
    private Integer rating;
    @Column
    private String comment;

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
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
