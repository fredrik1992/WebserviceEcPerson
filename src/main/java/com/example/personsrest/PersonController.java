package com.example.personsrest;

import com.example.personsrest.domain.Person;
import com.example.personsrest.remote.GroupRemote;
import com.example.personsrest.remote.GroupRemoteImpl;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    PersonService personService;


    @GetMapping("")
    public ResponseEntity<List<PersonDTO>> all (@RequestParam(value = "search",required = false) String search,
                                                @RequestParam(value = "pagenumber",required = false) String pagenumber,
                                                @RequestParam(value = "pagesize",required = false) String pagesize){

        List<PersonDTO> persons = personService.getAllPersons().stream().map(person -> new PersonDTO(person.getId(),person.getName(),person.getCity(),person.getAge(),null)).collect(Collectors.toList());

        if (search != null){

            List<PersonDTO> test  = personService.getPersonsSearch(search,pagenumber,pagesize).getContent().stream().map(person -> new PersonDTO(person.getId(),person.getName(),person.getCity(),person.getAge(),null) ).collect(Collectors.toList());
            return ResponseEntity.ok(test);

        }

        return ResponseEntity.ok(persons);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> person (@PathVariable String id){
        Person person = personService.getById(id).get();
        PersonDTO personDTO = getPersonDTO(person);
        return ResponseEntity.ok(personDTO);
    }
    @PostMapping("")
    public ResponseEntity<PersonDTO> add  (@RequestBody CreatePerson createPerson) throws JsonProcessingException {
        Person savedPerson = personService.createPerson(createPerson.getName(),createPerson.getCity(),createPerson.getAge());
        PersonDTO personDTO = new PersonDTO(savedPerson.getId(),savedPerson.getName(),savedPerson.getCity(),savedPerson.getAge(),null);
        return  ResponseEntity.ok(personDTO);

    }
    @PutMapping("/{personId}/addGroup/{groupName}")
    public ResponseEntity<PersonDTO> addGroup(@PathVariable String personId,@PathVariable String groupName){

        List <String> groups = personService.addGroup(personId,groupName);

        PersonDTO personDTO = new PersonDTO(personId,null,null,0,groups);
        return ResponseEntity.ok(personDTO);

    }

    @DeleteMapping("/{personId}/removeGroup/{groupName}")
    public ResponseEntity<PersonDTO> removeGroup(@PathVariable String personId,@PathVariable String groupName){

        List <String> groups = personService.removeGroup(personId,groupName);

        PersonDTO personDTO = new PersonDTO(null,null,null,0,groups);
        return ResponseEntity.ok(personDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@RequestBody CreatePerson createPerson, @PathVariable String id){
        Person updatedPerson = personService.update(id,createPerson.getName(),createPerson.getCity(),createPerson.getAge());
        PersonDTO personDTO = getPersonDTO(updatedPerson);
        return ResponseEntity.ok(personDTO);
    }

    private PersonDTO getPersonDTO(Person updatedPerson) {
        return new PersonDTO(updatedPerson.getId(),updatedPerson.getName(),updatedPerson.getCity(),updatedPerson.getAge(),null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){

         personService.deletePerson(id);
    }
    @Value
    static class CreatePerson {
        String id;
        String name;
        String city;
        int age;

    }

    @Data
    @AllArgsConstructor
    private class PersonDTO{
        private String id;
        private String name;
        private String city;
        private int age;
        List<String> groups;


    }

}

