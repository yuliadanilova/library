package com.study.library.dto;

import com.study.library.enums.RentStates;

import java.time.LocalDate;

public class RentDto {

    private Integer id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate deadlineDate;
    private ReviewDto review;
    private BookDto book;
    private RentStates states;
    private ClientDto client;


    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public ReviewDto getReview() {
        return review;
    }

    public void setReview(ReviewDto review) {
        this.review = review;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public RentStates getStates() {
        return states;
    }

    public void setStates(RentStates states) {
        this.states = states;
    }
}
