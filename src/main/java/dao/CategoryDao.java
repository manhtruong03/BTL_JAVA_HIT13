package main.java.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import main.java.model.Category;

public class CategoryDao {
    public static boolean saveCategories(List<Category> listOfCategory) {
	try {
	    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("data/Categories.txt")));
	    for (Category category : listOfCategory) {
		writer.print(category.getId() + "|");
		writer.print(category.getName() + "|");
		writer.print(category.getDescription() + "\n");
	    }
	    writer.close();
	    return true;
	} catch (IOException e) {
	    System.out.println("An error occurred while saving categories.");
	    return false;
	}
    }

    public static List<Category> loadCategories() {
	List<Category> listOfCategory = new ArrayList<>();
	try {
	    BufferedReader reader = new BufferedReader(new FileReader("data/Categories.txt"));
	    String line = reader.readLine();
	    while (line != null) {
		String[] parts = line.split("\\|");
		int id = Integer.parseInt(parts[0]);
		listOfCategory.add(new Category(id, parts[1], parts[2]));
		line = reader.readLine();
	    }
	    reader.close();
	} catch (FileNotFoundException e) {
	    System.out.println("Error: File not found");
	    e.printStackTrace();
	} catch (IOException e) {
	    System.out.println("Error: IOException occurred");
	    e.printStackTrace();
	} catch (NumberFormatException e) {
	    System.out.println("Error: Invalid number format");
	    e.printStackTrace();
	}
	return listOfCategory;
    }
}
