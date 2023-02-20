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
import main.java.model.Identifiable;
import main.java.model.Product;
import main.java.service.CategoryService;

public class ProductDao {
    public static boolean saveProduct(List<Product> listOfProduct) {
	try {
	    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("data/products.txt")));
	    for (Product product : listOfProduct) {
		writer.print(product.getId() + "|");
		writer.print(product.getName() + "|");
		writer.print(product.getQuantity() + "|");
		writer.print(product.getPrice() + "|");
		writer.print(product.getCategory().getId() + "\n");
	    }
	    writer.close();
	    return true;
	} catch (IOException e) {
	    System.out.println("An error occurred while saving categories.");
	    return false;
	}
    }

    public static List<Product> loadProduct() {
	List<Product> listOfProduct = new ArrayList<>();
	try {
	    BufferedReader reader = new BufferedReader(new FileReader("data/products.txt"));
	    String line = reader.readLine();
	    while (line != null) {
		String[] parts = line.split("\\|");
		int prod_id = Integer.parseInt(parts[0]);
		String name = parts[1];
		Identifiable identifiable = new Identifiable(prod_id, name);

		int quantity = Integer.parseInt(parts[2]);
		float price = Float.parseFloat(parts[3]);

		int cat_id = Integer.parseInt(parts[4]);
		Category category = new CategoryService().searchById(cat_id);

		listOfProduct.add(new Product(identifiable, quantity, price, category));
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
	return listOfProduct;
    }

}
