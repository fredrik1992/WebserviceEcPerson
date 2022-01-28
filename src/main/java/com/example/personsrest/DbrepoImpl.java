package com.example.personsrest;

import com.example.personsrest.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class DbrepoImpl implements  Dbrepo{
    @Override
    public Page<Person> findAllByNameContainingOrCityContaining(String name, String city, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Person> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Person> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Person> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Person> findAll() {
        return null;
    }

    @Override
    public Iterable<Person> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Person entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Person> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
