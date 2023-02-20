package main.java.service;

import java.util.Collections;
import java.util.List;

import main.java.dao.CategoryDao;
import main.java.model.Category;
import main.java.model.Identifiable;
import main.java.util.CompareByDesciption;
import main.java.util.Helper;

public class CategoryService extends DataManipulation {
    // Object's property
    private List<Category> listOfCategory;

    // Constructor
    public CategoryService() {
	super();
	this.listOfCategory = CategoryDao.loadCategories();
    }

    public CategoryService(List<Category> listOfCategory) {
	super();
	this.listOfCategory = listOfCategory;
    }

    // Getter and Setter
    public List<Category> getListOfCategory() {
	return listOfCategory;
    }

    public void setListOfCategory(List<Category> listOfCategory) {
	this.listOfCategory = listOfCategory;
    }

    // SORT
    @Override
    public boolean sort(List<? extends Identifiable> list, int choice) {
	switch (choice) {
	case 3:
	    Collections.sort(list, new CompareByDesciption<Identifiable>());
	    break;
	default:
	    super.sort(list, choice);
	}
	return true;
    }

    // SEARCH
    @Override
    public <T extends Identifiable> List<T> search(List<T> list, int choice) {
	if (!(list.get(0) instanceof Category)) {
	    return null;
	}
	List<T> searchResults = super.search(list, choice);
	switch (choice) {
	case 3:
	    String description = Helper.scanString("Description").trim().toLowerCase();
	    for (T entity : list) {
		Category category = (Category) entity;
		if (category.getDescription().trim().toLowerCase().contains(description)) {
		    searchResults.add(entity);
		}
	    }
	    break;
	default:
	    System.out.println("Invalid choice. Please try again!");
	}
	return searchResults;
    }

    // SEARCH - Category
    public Category searchById(int id) {
	return (Category) searchById(listOfCategory, id);
    }

    // VIEW title
    @Override
    public String title() {
	return super.title() + String.format("%-50s ", "DESCRIPTION");
    }

    // CREATE a Category
    @Override
    public Identifiable create() {
	Identifiable identifiable = super.create();
	String description = Helper.scanString("Description");
	return new Category(identifiable, description);
    }

    // VIEW - Options EDIT
    @Override
    public int showOptionsProperty() {
	int option = super.showOptionsProperty();
	System.out.println(" (" + (++option) + ") - Description");
	return option;
    }

    // EDIT
    @Override
    public boolean edit(Identifiable identifiable, int choice) {
	if (!(identifiable instanceof Category)) {
	    System.out.println("Input must be an instance of the Category class.");
	    return false;
	}
	Category category = (Category) identifiable;
	switch (choice) {
	case 3:
	    category.setDescription(Helper.scanString("new Description"));
	    break;
	default:
	    return super.edit(identifiable, choice);
	}
	return true;
    }

    // SAVE - File
    @Override
    public boolean saveFile() {
	return CategoryDao.saveCategories(listOfCategory);
    }
}
