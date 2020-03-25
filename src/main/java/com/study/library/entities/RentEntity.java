package com.study.library.entities;

import com.study.library.enums.RentStates;

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
    @OneToOne
    private ReviewEntity review;
    @Column
    private RentStates state;

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

    public ReviewEntity getReview() {
        return review;
    }

    public void setReview(ReviewEntity review) {
        this.review = review;
    }

    public RentStates getState() {
        return state;
    }

    public void setState(RentStates state) {
        this.state = state;
    }
}
