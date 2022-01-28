package com.example.personsrest;

import com.example.personsrest.domain.Person;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PersonImpl  implements Person  {
    private String id;
    private String name;
    private String city;
    private Integer age;
    List<String> groups = new ArrayList<>();

    private PersonImpl(PersonBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.city = builder.city;
        this.age = builder.age;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setActive(boolean active) {

    }

    @Override
    public List<String> getGroups() {
        return groups;
    }

    @Override
    public void addGroup(String groupId) {
        groups.add(groupId);
    }

    @Override
    public void removeGroup(String groupId) {
        groups = groups.stream().filter(groups -> !groups.equals(groupId)).collect(Collectors.toList());
    }
    public static class PersonBuilder{

        private String id;
        private String name;
        private String city;
        private Integer age;
        List<String> groups;

        public PersonBuilder id (String id ){
            this.id = id;
            return this;
        }
        public PersonBuilder name(String name ){
            this.name = name;
            return this;
        }
        public PersonBuilder city (String city ){
            this.city = city;
            return this;
        }
        public PersonBuilder age (Integer age){
            this.age = age;
            return this;
        }
        public Person build(){
            Person person = new PersonImpl(this);
            return person;
        }
    }
}
