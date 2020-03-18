package com.study.library.repositories;

import com.study.library.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer>, JpaSpecificationExecutor<BookEntity> {
}
