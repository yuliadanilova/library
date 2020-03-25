package com.study.library.repositories;

import com.study.library.entities.RentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<RentEntity, Integer> {
    Optional<RentEntity> findByBookIdAndClientId(Integer bookId, Integer clientId);
    List<RentEntity> findAll();
}
