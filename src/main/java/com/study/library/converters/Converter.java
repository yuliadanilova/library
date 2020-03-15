package com.study.library.converters;

import com.study.library.dto.AuthorDto;
import com.study.library.dto.BookDto;
import com.study.library.entities.AuthorEntity;
import com.study.library.entities.BookEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class Converter {

    public BookEntity convertBookDtoToBookEntity(BookEntity bookEntity, BookDto bookDto) {
        bookEntity.setName(bookDto.getName());
        bookEntity.setCount(bookDto.getCount());
        bookEntity.setAuthors(convertToAuthorEntities(bookDto.getAuthors()));
        return bookEntity;
    }

    private List<AuthorEntity> convertToAuthorEntities(List<AuthorDto> authors) {
       return authors
               .stream()
               .map(a -> {
                   AuthorEntity author = new AuthorEntity();
                   author.setId(a.getId());
                   return convertAuthorDtoToAuthorEntity(author, a);
               })
               .collect(Collectors.toList());
    }

    public AuthorEntity convertAuthorDtoToAuthorEntity(AuthorEntity authorEntity, AuthorDto authorDto) {
        authorEntity.setName(authorDto.getName());
        authorEntity.setDescription(authorDto.getDescription());
        authorEntity.setBooks(convertToBooksEntities(authorDto.getBooks()));
        return authorEntity;
    }

    private List<BookEntity> convertToBooksEntities(List<BookDto> books) {
        return books
                .stream()
                .map(b -> {
                    BookEntity book = new BookEntity();
                    book.setId(b.getId());
                    return convertBookDtoToBookEntity(book, b);
                })
                .collect(Collectors.toList());
    }
}
