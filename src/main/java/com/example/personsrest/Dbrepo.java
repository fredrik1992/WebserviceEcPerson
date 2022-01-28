package com.example.personsrest;

import com.example.personsrest.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface Dbrepo extends CrudRepository<Person,String> {
    Page<Person> findAllByNameContainingOrCityContaining(String name,String city, Pageable pageable);
}
