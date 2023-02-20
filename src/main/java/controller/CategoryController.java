package main.java.controller;

import java.util.List;

import main.java.model.Category;
import main.java.service.CategoryService;

public class CategoryController {
    // Object's property
    private CategoryService categoryService;

    // Constructor
    public CategoryController() {
	super();
	this.categoryService = new CategoryService();
    }

    public CategoryController(CategoryService categoryService) {
	super();
	this.categoryService = categoryService;
    }

    // Getter and Setter
    public CategoryService getCategoryService() {
	return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
	this.categoryService = categoryService;
    }

    // MANAGE - Category
    public void manageCategorys() {
	List<Category> listOfCategory = categoryService.getListOfCategory();
	HomeController.manage(listOfCategory, categoryService, "Category");
    }

}
