package main.java.model;

import java.util.HashMap;
import java.util.Map;

public class AccessLevel {
    // Constants of Permissions
    public static final int ADMIN = 3;
    public static final int SELLER = 2;
    public static final int NONE = 0;

    private static final Map<String, Integer> ROLE_MAP = new HashMap<>();
    static {
	ROLE_MAP.put("ADMIN", ADMIN);
	ROLE_MAP.put("SELLER", SELLER);
	ROLE_MAP.put("NONE", NONE);
    }

    // Object's property
    private String nameLevel;
    private int valueLevel;

    // Constructor
    public AccessLevel(Object accessLevel) {
	super();
	setAccessLevel(accessLevel);
    }

    // Getters and Setters
    public String getNameLevel() {
	return nameLevel;
    }

    public void setNameLevel(String nameLevel) {
	this.nameLevel = nameLevel;
    }

    public int getValueLevel() {
	return valueLevel;
    }

    public void setValueLevel(int valueLevel) {
	this.valueLevel = valueLevel;
    }

    // VIEW - HashTable
    public static void viewHashTable() {
	System.out.printf("%-20s %-10s\n", "KEY", "VALUE");
	for (Map.Entry<String, Integer> entry : ROLE_MAP.entrySet()) {
	    System.out.printf("%-20s %-10d\n", entry.getKey(), entry.getValue());
	}
    }

    // SET - by Integer or String
    public void setAccessLevel(Object accessLevel) {
	if (accessLevel instanceof Integer || accessLevel instanceof String) {
	    for (Map.Entry<String, Integer> entry : ROLE_MAP.entrySet()) {
		if (entry.getKey().equals(accessLevel) || entry.getValue() == accessLevel) {
		    setNameLevel(entry.getKey());
		    setValueLevel(entry.getValue());
		    return;
		}
	    }
	}
	setNameLevel("NONE");
	setValueLevel(NONE);
    }

}
