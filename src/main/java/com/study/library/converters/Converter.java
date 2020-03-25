package com.study.library.converters;

import com.study.library.dto.*;
import com.study.library.entities.AuthorEntity;
import com.study.library.entities.BookEntity;
import com.study.library.entities.ClientEntity;
import com.study.library.entities.RentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class Converter {

    public BookEntity convertBookDtoToBookEntity(BookEntity bookEntity, BookDto bookDto) {
        bookEntity.setName(bookDto.getName());
        bookEntity.setCount(bookDto.getCount());
        bookEntity.setYear(bookDto.getYear());
        bookEntity.setAuthors(convertToAuthorEntities(bookDto.getAuthors()));
        return bookEntity;
    }

    public AuthorEntity convertAuthorDtoToAuthorEntity(AuthorEntity authorEntity, AuthorDto authorDto) {
        authorEntity.setName(authorDto.getName());
        authorEntity.setDescription(authorDto.getDescription());
        return authorEntity;
    }

    public ClientEntity convertClientDtoToClientEntity(ClientDto client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(client.getId());
        clientEntity.setPassport(client.getPassport());
        clientEntity.setAge(client.getAge());
        clientEntity.setFirstName(client.getFirstName());
        clientEntity.setLastName(client.getLastName());
        return clientEntity;
    }

    public ClientDto convertClientEntityToClientDto(ClientEntity clientEntity) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientEntity.getId());
        clientDto.setAge(clientEntity.getAge());
        clientDto.setPassport(clientEntity.getPassport());
        clientDto.setFirstName(clientEntity.getFirstName());
        clientDto.setLastName(clientEntity.getLastName());
        return clientDto;
    }

    public BookDto convertBookEntityToBookDto(BookEntity book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setCount(book.getCount());
        bookDto.setYear(book.getYear());
        bookDto.setAuthors(convertToAuthorDtos(book.getAuthors()));
        return bookDto;
    }

    public AuthorDto convertAuthorEntityToAuthorDto(AuthorEntity a) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(a.getId());
        authorDto.setName(a.getName());
        authorDto.setDescription(a.getDescription());
        return authorDto;
    }

    private List<RentDto> convertRentsToRentsDto(List<RentEntity> rentEntities) {
        if (rentEntities == null) {
            return new ArrayList<>();
        }
        return rentEntities
                .stream()
                .map(this::convertRentEntityToRentDto)
                .collect(Collectors.toList());
    }

    public RentDto convertRentEntityToRentDto(RentEntity rent) {
        RentDto rentDto = new RentDto();
        rentDto.setId(rent.getId());
        rentDto.setClient(convertClientEntityToClientDto(rent.getClient()));
        rentDto.setStartDate(rent.getStartDate());
        rentDto.setFinishDate(rent.getFinishDate());
        rentDto.setDeadlineDate(rent.getDeadlineDate());
        rentDto.setStates(rent.getState());
        rentDto.setBook(convertBookEntityToBookDto(rent.getBook()));
        if (rent.getReview() != null)
            rentDto.setReview(new ReviewDto(rent.getReview().getId(),
                    rent.getReview().getRating(),
                    rent.getReview().getComment()));
        return rentDto;
    }

    private List<AuthorDto> convertToAuthorDtos(List<AuthorEntity> authors) {
        return authors
                .stream()
                .map(this::convertAuthorEntityToAuthorDto)
                .collect(Collectors.toList());
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

}
