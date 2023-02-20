package main.java.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.model.Identifiable;
import main.java.util.CompareById;
import main.java.util.CompareByName;
import main.java.util.Helper;

public class DataManipulation {

    // SAVE - File
    public boolean saveFile() {
	return false;
    }

    // SORT
    public boolean sort(List<? extends Identifiable> list, int choice) {
	switch (choice) {
	case 1:
	    Collections.sort(list, new CompareById());
	    break;
	case 2:
	    Collections.sort(list, new CompareByName());
	    break;
	default:
	    System.out.println("Invalid choice. Please try again!");
	    return false;
	}
	return true;
    }

    // SORT - by ID
    public void sortById(List<? extends Identifiable> list) {
	Collections.sort(list, new CompareById());
    }

    // SEARCH
    public <T extends Identifiable> List<T> search(List<T> list, int choice) {
	List<T> searchResults = new ArrayList<>();
	switch (choice) {
	case 1:
	    int id = Helper.scanInteger("ID");
	    if (checkIdExist(list, id)) {
		searchResults.add(list.get(getPositionById(list, id)));
	    }
	    break;
	case 2:
	    String name = Helper.scanString("Name").trim().toLowerCase();
	    for (T entity : list) {
		if (entity.getName().trim().toLowerCase().contains(name)) {
		    searchResults.add(entity);
		}
	    }
	    break;
	}
	return searchResults;
    }

    // SEARCH - get Position
    public int getPositionById(List<? extends Identifiable> list, int id) {
	return Collections.binarySearch(list, new Identifiable(id), new CompareById());
    }

    // SEARCH - by ID
    public Identifiable searchById(List<? extends Identifiable> list, int id) {
	int pos = getPositionById(list, id);
	if (pos < 0) {
	    System.out.println("ID \"" + id + "\" does not exist.");
	    return null;
	}
	return list.get(pos);
    }

    // CHECK ID
    public boolean checkIdExist(List<? extends Identifiable> list, int id) {
	return (getPositionById(list, id) >= 0) ? true : false;
    }

    // VIEW title
    public String title() {
	return String.format("%-10s %-25s ", "ID", "NAME");
    }

    // VIEW - Options EDIT
    public int showOptionsProperty() {
	int option = 0;
	System.out.println(" (" + (++option) + ") - ID");
	System.out.println(" (" + (++option) + ") - Name");
	return option;
    }

    // VIEW
    public <T> void view(List<T> list, String titleTable) {
	if (list.isEmpty()) {
	    System.out.println("Empty list!");
	    return;
	}
	System.out.println(titleTable);
	for (T entity : list) {
	    System.out.println(entity);
	}
    }

    public void view(Identifiable identifiable, String titleTable) {
	System.out.println(titleTable);
	System.out.println(identifiable);
    }

    // ADD
    public <T extends Identifiable> boolean add(List<T> list, T identifiable) {
	if (identifiable == null) {
	    return false;
	}
	if (checkIdExist(list, identifiable.getId())) {
	    System.out.println("ID \"" + identifiable.getId() + "\" already exists.");
	    return false;
	}
	return list.add(identifiable);
    }

    // REMOVE
    public boolean remove(List<? extends Identifiable> list, int id) {
	if (checkIdExist(list, id)) {
	    // Confirm remove
	    if (Helper.confirm("Do you want to coninue?")) {
		list.remove(getPositionById(list, id));
		return true;
	    }
	    return false;
	}
	System.out.println("ID \"" + id + "\" does not exist.");
	return false;
    }

    // CREATE
    public Identifiable create() {
	int id = Helper.scanInteger("new ID");
	String name = Helper.scanString("new Name");
	return new Identifiable(id, name);
    }

    // EDIT
    public boolean edit(Identifiable identifiable, int choice) {
	switch (choice) {
	case 1:
	    identifiable.setId(Helper.scanInteger("new ID"));
	    break;
	case 2:
	    identifiable.setName(Helper.scanString("new Name"));
	    break;
	default:
	    System.out.println("Invalid choice. Please try again!");
	}
	return true;
    }
}
