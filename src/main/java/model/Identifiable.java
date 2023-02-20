package main.java.model;

public class Identifiable {
    // Object's property
    private int id;
    private String name;

    // Constructor
    public Identifiable() {
	super();
    }

    public Identifiable(int id) {
	super();
	this.id = id;
    }

    public Identifiable(int id, String name) {
	super();
	this.id = id;
	this.name = name;
    }

    public Identifiable(Identifiable identifiable) {
	super();
	this.id = identifiable.getId();
	this.name = identifiable.getName();
    }

    // Getter and Setter
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return String.format("%-10d %-25s ", id, name);
    }
}
