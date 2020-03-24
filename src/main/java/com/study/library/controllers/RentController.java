package com.study.library.controllers;


import com.study.library.dto.ReviewDto;
import com.study.library.entities.BookEntity;
import com.study.library.entities.ClientEntity;
import com.study.library.entities.RentEntity;
import com.study.library.exceptions.NotFoundException;
import com.study.library.repositories.BookRepository;
import com.study.library.repositories.ClientRepository;
import com.study.library.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;


@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentRepository rentRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public RentController(RentRepository rentRepository, BookRepository bookRepository, ClientRepository clientRepository) {
        this.rentRepository = rentRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/{bookId}/{clientId}")
    public void rent(@PathVariable Integer bookId, @PathVariable Integer clientId) {
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
                rentRepository.save(rentEntity);
                bookEntity.setCount(--count);
                bookRepository.save(bookEntity);
            }
        }
    }

    @DeleteMapping("/{bookId}/{clientId}")
    public void returnBook(@PathVariable Integer bookId, @PathVariable Integer clientId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Can not find book with id: " + bookId));
        Optional<RentEntity> byBookIdAndClientId = rentRepository.findByBookIdAndClientId(bookId, clientId);
        Integer count = bookEntity.getCount();
        if (byBookIdAndClientId.isPresent() && byBookIdAndClientId.get().getFinishDate() == null) {
            RentEntity rentEntity = byBookIdAndClientId.get();
            rentEntity.setFinishDate(LocalDate.now());
            rentRepository.save(rentEntity);
            bookEntity.setCount(++count);
            bookRepository.save(bookEntity);
        } else {
            throw new NotFoundException("Can not find information");
        }
    }

    @PutMapping("/{bookId}/{clientId}")
    public void reviewBook(@PathVariable Integer bookId, @PathVariable Integer clientId, @RequestBody ReviewDto review) {
        Optional<RentEntity> byBookIdAndClientId = rentRepository.findByBookIdAndClientId(bookId, clientId);
        if (byBookIdAndClientId.isPresent()) {
            RentEntity rentEntity = byBookIdAndClientId.get();
            rentEntity.setRating(review.getRating());
            rentEntity.setComment(review.getComment());
            rentRepository.save(rentEntity);
        } else {
            throw new NotFoundException("Can not find rent information");
        }
    }


}
