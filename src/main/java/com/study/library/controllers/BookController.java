package com.study.library.controllers;

import com.study.library.dto.BookDto;
import com.study.library.entities.BookEntity;
import com.study.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody BookDto bookDto) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookDto.getName());
        bookEntity.setComment(bookDto.getComment());
        this.bookRepository.save(bookEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity list() {
        Iterable<BookEntity> all = this.bookRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
