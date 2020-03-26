package com.study.library.controllers;


import com.study.library.converters.Converter;
import com.study.library.dto.RentDto;
import com.study.library.dto.ReviewDto;
import com.study.library.entities.BookEntity;
import com.study.library.entities.ClientEntity;
import com.study.library.entities.RentEntity;
import com.study.library.enums.RentStates;
import com.study.library.exceptions.NotFoundException;
import com.study.library.repositories.BookRepository;
import com.study.library.repositories.ClientRepository;
import com.study.library.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentRepository rentRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final Converter converter;

    @Autowired
    public RentController(RentRepository rentRepository, BookRepository bookRepository, ClientRepository clientRepository, Converter converter) {
        this.rentRepository = rentRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity listRents(){
        List<RentDto> rentDtos = rentRepository
                .findAll()
                .stream()
                .map(converter::convertRentEntityToRentDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rentDtos);
    }

    @PostMapping("/{bookId}/{clientId}")
    public ResponseEntity rent(@PathVariable Integer bookId, @PathVariable Integer clientId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Can not find book with id: " + bookId));
        Integer count = bookEntity.getCount();
        if (count > 0) {
            ClientEntity clientEntity = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Can not find client with id: " + clientId));
            Optional<RentEntity> byBookIdAndClientId = rentRepository.findByBookIdAndClientId(bookId, clientId);
            if (!byBookIdAndClientId.isPresent()) {
                RentEntity rentEntity = new RentEntity();
                rentEntity.setBook(bookEntity);
                rentEntity.setClient(clientEntity);
                LocalDate date = LocalDate.now();
                rentEntity.setStartDate(date);
                rentEntity.setDeadlineDate(date.plusMonths(1));
                rentEntity.setState(RentStates.ACTIVELY);
                rentRepository.save(rentEntity);
                bookEntity.setCount(--count);
                bookRepository.save(bookEntity);
                return ResponseEntity.ok(converter.convertRentEntityToRentDto(rentEntity));
            }
        }
        return null;
    }

    @PutMapping("/{bookId}/{clientId}")
    public void returnBook(@PathVariable Integer bookId, @PathVariable Integer clientId, @RequestBody ReviewDto review) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Can not find book with id: " + bookId));
        Optional<RentEntity> byBookIdAndClientId = rentRepository.findByBookIdAndClientId(bookId, clientId);
        Integer count = bookEntity.getCount();
        if (byBookIdAndClientId.isPresent() && byBookIdAndClientId.get().getFinishDate() == null) {
            RentEntity rentEntity = byBookIdAndClientId.get();
            rentEntity.setFinishDate(LocalDate.now());
            rentEntity.getReview().setComment(review.getComment());
            rentEntity.getReview().setRating(review.getRating());
            rentEntity.setState(RentStates.COMPLETED);
            rentRepository.save(rentEntity);
            bookEntity.setCount(++count);
            bookRepository.save(bookEntity);
        } else {
            throw new NotFoundException("Can not find information");
        }
    }

   /* @PutMapping("/{bookId}/{clientId}")
    public void reviewBook(@PathVariable Integer bookId, @PathVariable Integer clientId, @RequestBody ReviewDto review) {
        Optional<RentEntity> byBookIdAndClientId = rentRepository.findByBookIdAndClientId(bookId, clientId);
        if (byBookIdAndClientId.isPresent()) {
            RentEntity rentEntity = byBookIdAndClientId.get();
            rentEntity.getReview().setComment(review.getComment());
            rentEntity.getReview().setRating(review.getRating());
            rentRepository.save(rentEntity);
        } else {
            throw new NotFoundException("Can not find rent information");
        }
    }*/


}
