package com.example.personsrest;

import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.*;
import java.util.stream.Collectors;

public class PersonRepositoryImpl  implements PersonRepository  {
    @Autowired Dbrepo dbrepo;
    Map<String,Person> personList = new HashMap<String,Person>();
    @Override
    public Optional<Person> findById(String id) {
        return Optional.of(personList.get(id));
    }

    @Override
    public List<Person> findAll() {
         return new ArrayList<>(personList.values());
    }

    @Override
    public Page<Person> findAllByNameContainingOrCityContaining(String name, String city, Pageable pageable) {

        return dbrepo.findAllByNameContainingOrCityContaining(name,city,pageable);

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Person save(Person person) {
        personList.put(person.getId(),person);
        return personList.get(person.getId());
    }

    @Override
    public void delete(String id) {
        personList.remove(id);
    }
}
