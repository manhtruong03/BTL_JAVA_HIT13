package main.java.service;

import java.util.Collections;
import java.util.List;

import main.java.model.Identifiable;
import main.java.model.Person;
import main.java.util.CompareByAddress;
import main.java.util.CompareByAge;
import main.java.util.CompareByPhoneNumber;
import main.java.util.Helper;

public class PersonService extends DataManipulation {

    // SORT
    @Override
    public boolean sort(List<? extends Identifiable> list, int choice) {
	switch (choice) {
	case 3:
	    Collections.sort(list, new CompareByAge<Identifiable>());
	    break;
	case 4:
	    Collections.sort(list, new CompareByPhoneNumber<Identifiable>());
	    break;
	case 5:
	    Collections.sort(list, new CompareByAddress<Identifiable>());
	    break;
	default:
	    super.sort(list, choice);
	}
	return true;
    }

    // SEARCH
    @Override
    public <T extends Identifiable> List<T> search(List<T> list, int choice) {
	if (!(list.get(0) instanceof Person)) {
	    return null;
	}
	List<T> searchResults = super.search(list, choice);
	switch (choice) {
	case 3:
	    int age = Helper.scanInteger("Age");
	    for (T entity : list) {
		Person person = (Person) entity;
		if (person.getAge() == age) {
		    searchResults.add(entity);
		}
	    }
	    break;
	case 4:
	    String phoneNumber = Helper.scanString("Phone number");
	    for (T entity : list) {
		Person person = (Person) entity;
		if (person.getPhoneNumber().equals(phoneNumber)) {
		    searchResults.add(entity);
		}
	    }
	    break;
	case 5:
	    String address = Helper.scanString("Address");
	    for (T entity : list) {
		Person person = (Person) entity;
		if (person.getAddress().equals(address)) {
		    searchResults.add(entity);
		}
	    }
	    break;
	}
	return searchResults;
    }

    // VIEW title
    @Override
    public String title() {
	return super.title() + String.format("%-10S %-15s %-25s ", "AGE", "PHONE", "ADDRESS");
    }

    // CREATE a Person
    @Override
    public Identifiable create() {
	Identifiable identifiable = super.create();
	int age = Helper.scanInteger("AGE");
	String phoneNumber = Helper.scanString("PhoneNumber");
	String address = Helper.scanString("ADDRESS");
	return new Person(identifiable, age, phoneNumber, address);
    }

    // VIEW - Options EDIT
    @Override
    public int showOptionsProperty() {
	int option = super.showOptionsProperty();
	System.out.println(" (" + (++option) + ") - Age");
	System.out.println(" (" + (++option) + ") - Phone Number");
	System.out.println(" (" + (++option) + ") - Address");
	return option;
    }

    // EDIT
    @Override
    public boolean edit(Identifiable identifiable, int choice) {
	if (!(identifiable instanceof Person)) {
	    System.out.println("Input must be an instance of the Person class.");
	    return false;
	}
	Person person = (Person) identifiable;
	switch (choice) {
	case 3:
	    person.setAge(Helper.scanInteger("new Age"));
	    break;
	case 4:
	    person.setPhoneNumber(Helper.scanString("new PhoneNumber"));
	    break;
	case 5:
	    person.setAddress(Helper.scanString("new Address"));
	    break;
	default:
	    return super.edit(identifiable, choice);
	}
	return true;
    }

    // SAVE - File
    @Override
    public boolean saveFile() {
	return false;
    }

}
