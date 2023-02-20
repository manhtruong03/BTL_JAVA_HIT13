package main.java.model;

public class Person extends Identifiable {
    // Object's property
    private int age;
    private String phoneNumber;
    private String address;

    // Constructor
    public Person() {
	super();
    }

    public Person(int id, String name, int age, String phoneNumber, String address) {
	super(id, name);
	this.age = age;
	this.phoneNumber = phoneNumber;
	this.address = address;
    }

    public Person(Identifiable identifiable, int age, String phoneNumber, String address) {
	super(identifiable);
	this.age = age;
	this.phoneNumber = phoneNumber;
	this.address = address;
    }

    public Person(Person person) {
	super(person.getId(), person.getName());
	this.age = person.getAge();
	this.phoneNumber = person.getPhoneNumber();
	this.address = person.getAddress();
    }

    // Getters and Setters
    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    @Override
    public String toString() {
	return super.toString() + String.format("%-10d %-15s %-25s ", age, phoneNumber, address);
    }

}
