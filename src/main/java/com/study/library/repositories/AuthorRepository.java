package com.study.library.repositories;

import com.study.library.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {
    List<AuthorEntity> findAll();
}
