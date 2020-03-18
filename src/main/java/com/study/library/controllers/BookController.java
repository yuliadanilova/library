package com.study.library.controllers;

import com.study.library.dto.AuthorDto;
import com.study.library.dto.BookFilterDto;
import com.study.library.exceptions.NotFoundException;
import com.study.library.converters.Converter;
import com.study.library.dto.BookDto;
import com.study.library.entities.BookEntity;
import com.study.library.repositories.AuthorRepository;
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
    private final AuthorRepository authorRepository;
    private final Converter converter;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, Converter converter) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody BookDto bookDto) {
        List<AuthorDto> authors = bookDto.getAuthors();
        if (authors != null && authors.size() > 0)   {
            authors.forEach(a -> {});
        }
        this.bookRepository.save(converter.convertBookDtoToBookEntity(new BookEntity(), bookDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public BookEntity bookEntity(@PathVariable Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Can not find book with id: " + id));
    }

    @GetMapping("/searchByFilter")
    public ResponseEntity search(@RequestBody BookFilterDto bookFilterDto) {
      List<BookDto> books = new ArrayList<>();
      bookRepository.findAll(byFilter(bookFilterDto)).forEach(book -> books.add(converter.convertBookEntityToBookDto(book)));
      return ResponseEntity.ok(books);
    }

    @GetMapping("/available")
    public List<BookEntity> getAllAvailableBooks() {
        List<BookEntity> bookEntityList = new ArrayList<>();
        Iterable<BookEntity> all = bookRepository.findAll();
        all.forEach(bookEntityList:: add);
        return bookEntityList.stream().filter(b -> b.getCount() > 0).collect(Collectors.toList());
    }

    @GetMapping
    public ResponseEntity getAllBooks() {
        Iterable<BookEntity> all = bookRepository.findAll();
        return ResponseEntity.ok(all);
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
