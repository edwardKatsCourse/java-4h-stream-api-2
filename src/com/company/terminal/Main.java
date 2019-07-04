package com.company.terminal;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> studentsGroup1 = new Student.StudentGenerator()
                .getRandomStudents(100)
                .stream()
                .peek(x -> x.setGroup("group 1"))
                .collect(Collectors.toList());

        List<Student> studentsGroup2 = new Student.StudentGenerator()
                .getRandomStudents(150)
                .stream()
                .peek(x -> x.setGroup("group 2"))
                .collect(Collectors.toList());

        List<Student> studentsGroup3 = new Student.StudentGenerator()
                .getRandomStudents(1500)
                .stream()
                .peek(x -> x.setGroup("group 3"))
                .collect(Collectors.toList());

        List<List<Student>> allStudents = Arrays.asList(studentsGroup1, studentsGroup2, studentsGroup3);

        allStudents.stream()
//                .filter(x -> x.)
                .flatMap(x -> x.stream())  //flattening -> уплощение ({1,2,3}, {4,5,6} -> {1,2,3,4,5,6})
//                .collect(Collectors.toCollection(LinkedList::new));
                .collect(Collectors.toMap(
                        x -> x.getGroup(), //x = student
                        y -> y,             //y = student,
                        (duplicateStudent1, duplicateStudent2) -> duplicateStudent1,
                        TreeMap::new
                ));

        //toMap(key, value)
        //toMap(key, value, (duplicate1, duplicate2) -> which key to choose)
        //toMap(key, value, key duplication, Map type -> TreeMap::new)

        String[] words = {"apple", "cherry", "banana"};
        String singleWord = Arrays.asList(words)
                .stream()
                .collect(Collectors.joining(", ", "fruit [", "]"));
        System.out.println(singleWord);




        allStudents = Arrays.asList(studentsGroup1, studentsGroup2, studentsGroup3);
        allStudents.stream()
                .flatMap(x -> x.stream()) //Stream<List<Student>> -> Stream<Student>
                .collect(
                        Collectors.groupingBy(
                                x -> x.getScore().intValue(),
                                Collectors.mapping(student -> student, Collectors.toList())))
                //Map<Score, List<Student>> ->
                // 1, [students with score 1..1.99]
                // 2, [students with score 2..2.99]
                // 3, [students with score 3..3.99]
                // 4, [students with score 4..4.99]
                // 5, [students with score 5..5.99]


                .entrySet()//Set<Entry<Key, Value>>>
                .stream()
                .filter(x -> x.getKey() >= 4.5)
                .map(x -> x.getValue()) //Stream<List<Student>>
                .flatMap(Collection::stream) //Stream<List<Student>> -> Stream<Student>
                .collect(Collectors.groupingBy(
                        Student::getGender, //group by gender: male and female
//                        Collectors.mapping(student -> student, Collectors.toList())       // -> Map<gender, List<Student>>
                        Collectors.counting()                                               // -> Map<gender, List<Student>.size>
                ))
                .forEach((key, value) -> System.out.printf("%s - %s\n", key, value));

        //Collectors.groupingBy() -> returns Map<>


        //4 - 5
        //compare girls vs boys


    }

    private void reduce() {
        //MapReduce (.map, .reduce)

        //Reduce
        //a + b: 100 elements -> 1
        //initial value, a + b

        //                                a(6)   b
        Integer integer = Arrays.asList(0, 0, 0, 0).stream()
                .reduce((a, b) -> a + b)
                .get();

        //Optional
        String s = null;

        s = Optional.ofNullable(s)
                .map(x -> x.toLowerCase())
                .filter(x -> x.length() > 15)
                .orElse("default value");

        System.out.println(s);


        System.out.println(integer);
    }
}

class EntrySet<K, V> {

    private K key;
    private V value;


    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
