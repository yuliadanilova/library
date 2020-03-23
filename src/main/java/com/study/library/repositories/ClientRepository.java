package com.study.library.repositories;

import com.study.library.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByPassport(Long passport);
    List<ClientEntity> findAll();
}
