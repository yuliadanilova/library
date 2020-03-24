package com.study.library.controllers;

import com.study.library.dto.BookFilterDto;
import com.study.library.exceptions.NotFoundException;
import com.study.library.converters.Converter;
import com.study.library.dto.BookDto;
import com.study.library.entities.BookEntity;
import com.study.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.study.library.repositories.FilterSpecifications.byFilter;


@RestController()
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final Converter converter;

    @Autowired
    public BookController(BookRepository bookRepository, Converter converter) {
        this.bookRepository = bookRepository;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody BookDto bookDto) {
        BookEntity bookEntity = converter.convertBookDtoToBookEntity(new BookEntity(), bookDto);
        this.bookRepository.save(bookEntity);
        return ResponseEntity.ok(converter.convertBookEntityToBookDto(bookEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity bookEntity(@PathVariable Integer id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Can not find book with id: " + id));
        return ResponseEntity.ok(converter.convertBookEntityToBookDto(bookEntity));
    }

    @GetMapping
    public ResponseEntity search(BookFilterDto bookFilterDto) {
        List<BookDto> books = bookRepository
                .findAll(byFilter(bookFilterDto))
                .stream()
                .map(converter::convertBookEntityToBookDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @PutMapping
    public BookEntity update(@RequestBody BookDto bookDto) {
       return bookRepository.findById(bookDto.getId())
            .map(bookEntity -> bookRepository.save(converter.convertBookDtoToBookEntity(bookEntity, bookDto)))
            .orElseGet(() -> {
              BookEntity newBook = new BookEntity();
              newBook.setId(bookDto.getId());
              return bookRepository.save(converter.convertBookDtoToBookEntity(newBook, bookDto));
           });
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Can not find book with id: " + id));
        bookRepository.delete(bookEntity);
    }

}
