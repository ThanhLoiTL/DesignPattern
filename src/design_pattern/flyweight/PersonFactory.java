package design_pattern.flyweight;

import model.Person;
import model.Student;
import model.Teacher;

import java.util.HashMap;

public class PersonFactory {
    private static final HashMap<PersonType, Person> persons = new HashMap<>();

    public static Person createPerson(PersonType personType) {
        Person person = null;
        if(persons.containsKey(personType)){
            person = persons.get(personType);
        }else {
            switch (personType) {
                case STUDENT:
                    person = new Student();
                    break;
                case TEACHER:
                    person = new Teacher();
                    break;
                default:
                    System.out.println("Unreachable code!");
            }
            persons.put(personType, person);
        }
        return person;
    }
}
