package main.java.model;

public class Category extends Identifiable {
    // Object's property
    private String description; // Mo ta danh muc

    // Constructor
    public Category() {
	super();
    }

    public Category(int id, String name, String description) {
	super(id, name);
	this.description = description;
    }

    public Category(Identifiable identifiable, String description) {
	super(identifiable);
	this.description = description;
    }

    // Getters and Setters
    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return super.toString() + String.format("%-50s ", description);
    }

}
