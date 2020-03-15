package com.study.library.controllers;
import com.study.library.converters.Converter;
import com.study.library.dto.AuthorDto;
import com.study.library.entities.AuthorEntity;
import com.study.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final Converter converter;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, Converter converter) {
        this.authorRepository = authorRepository;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody AuthorDto authorDto) {
        this.authorRepository.save(converter.convertAuthorDtoToAuthorEntity(new AuthorEntity(), authorDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity list() {
        Iterable<AuthorEntity> all = this.authorRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping
    public AuthorEntity update(@RequestBody AuthorDto authorDto) {
        return authorRepository.findById(authorDto.getId())
                .map(authorEntity -> authorRepository.save(converter.convertAuthorDtoToAuthorEntity(authorEntity, authorDto)))
                .orElseGet(() -> {
                    AuthorEntity newAuthor = new AuthorEntity();
                    newAuthor.setId(authorDto.getId());
                    return authorRepository.save(converter.convertAuthorDtoToAuthorEntity(newAuthor, authorDto));
                });
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        AuthorEntity authorEntity = authorRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author id:" + id));
        authorRepository.delete(authorEntity);
    }

}
