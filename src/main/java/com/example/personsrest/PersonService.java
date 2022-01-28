package com.example.personsrest;

import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonRepository;
import com.example.personsrest.remote.GroupRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    GroupRemote groupRemote;


    public Optional<Person> getById(String id) {
        Person person1 = new PersonImpl.PersonBuilder().id(id).build();
        personRepository.save(person1);
        Optional<Person> person = personRepository.findById(id);
        return Optional.of(person.get());
    }

    public Person createPerson(String name, String city, int age) {

        String corrId = UUID.randomUUID().toString();
        Person person = new PersonImpl.PersonBuilder().id(corrId).name(name).city(city).age(age).build();


        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {

        return personRepository.findAll();
    }

    public Person update(String id, String name, String city, int age) {

        Person person1 = personRepository.findById(id).get();
        if (name != null) person1.setName(name);
        if (city != null) person1.setCity(city);
        if (age != 0) person1.setAge(age);
        person1.setAge(age);

        return personRepository.save(person1);
    }

    public void deletePerson(String id) {
        personRepository.delete(id);
    }

    public List<String> addGroup(String personId, String groupName) {
        String groupId = groupRemote.createGroup(groupName);
        Person fetchedPerson = personRepository.findById(personId).get(); //person
        fetchedPerson.addGroup(groupId);


        return personRepository.save(fetchedPerson).getGroups().stream().map(group -> groupRemote.getNameById(group)).collect(Collectors.toList());
    }

    public List<String> removeGroup(String personId, String groupName) {

        Person fetchedPerson = personRepository.findById(personId).get();

        fetchedPerson.removeGroup(groupName);
        return personRepository.save(fetchedPerson).getGroups().stream()
                .filter(groupId -> !groupRemote.getNameById(groupId).equals(groupName))
                .collect(Collectors.toList());
    }

    public Page<Person> getPersonsSearch(String search, String pagenumber, String pagesize) {

        PageRequest pageRequest = PageRequest.of(Integer.parseInt(pagenumber), Integer.parseInt(pagesize));

        return personRepository.findAllByNameContainingOrCityContaining(search, search, pageRequest);

    }

    public Page<Person> findByName(String string, PageRequest pageRequest) {
        return personRepository.findAllByNameContainingOrCityContaining(string, null, pageRequest);
    }
}
