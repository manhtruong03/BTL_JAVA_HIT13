package main.java.model;

public class Account extends Person {
    // Object's property
    private String username;
    private String password;
    private AccessLevel accessLevel; // Quyen truy cap

    // Constructors
    public Account() {
	super();
    }

    public Account(Person person, String username, String password, AccessLevel accessLevel) {
	super(person);
	this.username = username;
	this.password = password;
	this.accessLevel = accessLevel;
    }

    public Account(Person person, String username, String password, Object accessLevel) {
	super(person);
	this.username = username;
	this.password = password;
	this.accessLevel = new AccessLevel(accessLevel);
    }

    // Getters and Setters
    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public AccessLevel getAccessLevel() {
	return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
	this.accessLevel = accessLevel;
    }

    public void setAccessLevel() {
	this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
	return super.toString() + String.format("%-15s %-15s %-15s ", username, password, accessLevel.getNameLevel());
    }

}
