package main.java.util;

import java.util.Comparator;

import main.java.model.Identifiable;
import main.java.model.Person;

public class CompareByPhoneNumber<T extends Identifiable> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
	Person person1 = (Person) o1;
	Person person2 = (Person) o2;
	return person1.getPhoneNumber().compareTo(person2.getPhoneNumber());
    }

}
