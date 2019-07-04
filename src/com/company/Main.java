package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //Stream API
        //потоки в Java

        //sources
        //pipes
        //terminal operations
        //- collect                         -> Some collection
        //     * toList, toSet
        //     * toMap
        //     * mappingBy
        //     * groupingBy


        //- findFirst, findAny - Optional
        //- reduce
        //- count


        //Stream API
        //Input/Output Stream

        //Multi-Threading - нить (много-нитиевость)

        Arrays.asList("Letter", "cusTomer", "Customer", "pRinter", "Java", "laptop", "screen")
                .stream()
                //"builder"
                .filter(x -> x.length() > 4)
                .map(x -> x.toLowerCase())
                .sorted()
                //"builder"
                .count(); //long


        Person person = Person
                //source
                .builder()

                //pipe
                .create()
                .name("John")
                .age(54)
                .city("Haifa")
                //pipe

                //terminal operation
                .build();


        //new MySuperClass(true, 0, 15, (short) 11, false, false, null, null)

    }
}

class Person {
    private String name;
    private Integer age;
    private String city;

    private Person(String name, Integer age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    private Person() {
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    private void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }


    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

//    Person person = Person
//            .builder()
//            .create()
//            .name("John")
//            .age(54)
//            .city("Haifa")
//            .build();
    static class PersonBuilder {

        private Person person;

        public PersonBuilder create() {
            person = new Person();
            return this;
        }

        public PersonBuilder name(String name) { this.person.setName(name); return this; }

        public PersonBuilder age(Integer age) { this.person.setAge(age); return this; }

        public PersonBuilder city(String city) { this.person.setCity(city); return this; }

        public Person build() { return person; }
    }
}
