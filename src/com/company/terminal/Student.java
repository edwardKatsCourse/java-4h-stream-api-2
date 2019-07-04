package com.company.terminal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student {

    private String name;
    private Double score; //оценка
    private String gender;

    private String group;

    public Student(String name, Double score, String gender) {
        this.name = name;
        this.score = score;
        this.gender = gender;
    }

    public Student(String name, Double score, String gender, String group) {
        this.name = name;
        this.score = score;
        this.gender = gender;
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Score: %s | Gender: %s | Group: %s",
                this.name,
                this.score,
                this.gender,
                this.group);
    }


    public static class StudentGenerator {
        private static final String [] MALE_NAMES = {
                "Peter",
                "James",
                "David",
                "Jacob",
                "Andrew",
                "Mark"
        };

        private static final String [] FEMALE_NAMES = {
            "Sarah",
            "Sofia",
            "Marry",
            "Anna",
            "Rita"
        };

        private static final String [] GENDERS = {
                "MALE",
                "FEMALE"
        };

        private static final Double MIN_SCORE = 1.0;
        private static final Double MAX_SCORE = 5.1;

        private String getRandomGender() {
            Random random = new Random();
            int randomGenderIndex = random.nextInt(GENDERS.length); //2 - 0..1

            return GENDERS[randomGenderIndex];
        }

        private String getRandomName(String [] names) { //MALE_NAMES or FEMALE_NAMES
            Random random = new Random();
            int randomNameIndex = random.nextInt(names.length);
            return names[randomNameIndex];
        }


        private Double getRandomScore() {
            Double score = ThreadLocalRandom.current().nextDouble(MIN_SCORE, MAX_SCORE);
            DecimalFormat df = new DecimalFormat("#.##");
            score = Double.valueOf(df.format(score));

            Random random = new Random();
            boolean getsPromotion = random.nextInt(100) <= 5;
            //с вероятностью 20%

            if (score < 4.5 && getsPromotion) {
                score = 5.0;
            }

            return score;
        }

        public Student getRandomStudent() {
            String gender = getRandomGender();
            String [] names = gender.equalsIgnoreCase("male") ? MALE_NAMES : FEMALE_NAMES;
            String name = getRandomName(names);
            Double score = getRandomScore();

//            if (gender.equalsIgnoreCase("male")) {
//                name = getRandomName(MALE_NAMES);
//            } else {
//                name = getRandomName(FEMALE_NAMES);
//            }
            return new Student(name, score, gender);
        }

        public List<Student> getRandomStudents(int count) {
//            List<Student> students = new ArrayList<>();
//            for (int i = 0; i < count; i++) {
//                students.add(getRandomStudent());
//            }

            return Stream.generate(this::getRandomStudent)
                    .limit(count)
                    .collect(Collectors.toList());
        }

    }
}
