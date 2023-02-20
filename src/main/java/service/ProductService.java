package main.java.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.dao.ProductDao;
import main.java.model.Category;
import main.java.model.Identifiable;
import main.java.model.Product;
import main.java.util.CompareByCategory;
import main.java.util.CompareByPrice;
import main.java.util.CompareByQuantity;
import main.java.util.Helper;

public class ProductService extends DataManipulation {
    // Object's property
    private List<Product> listOfProduct;

    // Constructor
    public ProductService() {
	super();
	this.listOfProduct = ProductDao.loadProduct();
    }

    public ProductService(List<Product> listOfProduct) {
	super();
	this.listOfProduct = listOfProduct;
    }

    // Getter and Setter
    public List<Product> getListOfProduct() {
	return listOfProduct;
    }

    public void setListOfProduct(List<Product> listOfProduct) {
	this.listOfProduct = listOfProduct;
    }

    // TITLE
    @Override
    public String title() {
	return super.title() + String.format("%-15s %-15s %-25s ", "QUANTITY", "PRICE", "CATEGORY");
    }

    public String titleBill() {
	return String.format("%-5s ", "NUM") + title() + String.format("%-20s ", "TOTAL");
    }

    // VIEW - Bill
    public List<String> billLines() {
	List<String> billLines = new ArrayList<>();
	if (listOfProduct.isEmpty()) {
	    billLines.add("Empty list!");
	    return billLines;
	}
	billLines.add(titleBill());
	int number = 0;
	float totalAmount = 0;
	for (Product product : listOfProduct) {
	    // STT
	    String line = String.format("%-10d ", ++number);
	    // Product
	    line += product.toString();
	    // Total price of one Product
	    line += String.format("%-20.2f", product.getTotal());
	    // Total amount
	    totalAmount += product.getTotal();
	    billLines.add(line);
	}
	String line = String.format("\n%40s %-20.2f", "Total amount: ", totalAmount);
	billLines.add(line);
	return billLines;
    }

    // CREATE a Product
    @Override
    public Identifiable create() {
	Identifiable identifiable = super.create();
	int quantity = Helper.scanInteger("Quantity");
	float price = Helper.scanFloat("Price");
	int cat_id = Helper.scanInteger("ID of Category");
	Category category = new CategoryService().searchById(cat_id);
	return (Product) new Product(identifiable, quantity, price, category);
    }

    // SORT
    @Override
    public boolean sort(List<? extends Identifiable> list, int choice) {
	switch (choice) {
	case 3:
	    Collections.sort(list, new CompareByQuantity<Identifiable>());
	    break;
	case 4:
	    Collections.sort(list, new CompareByPrice<Identifiable>());
	    break;
	case 5:
	    Collections.sort(list, new CompareByCategory<Identifiable>());
	    break;
	default:
	    super.sort(list, choice);
	}
	return true;
    }

    // SEARCH
    @Override
    public <T extends Identifiable> List<T> search(List<T> list, int choice) {
	if (!(list.get(0) instanceof Product)) {
	    return null;
	}
	List<T> searchResults = super.search(list, choice);
	switch (choice) {
	case 3:
	    int quantity = Helper.scanInteger("Quantity");
	    for (T entity : list) {
		Product product = (Product) entity;
		if (product.getQuantity() == quantity) {
		    searchResults.add(entity);
		}
	    }
	    break;
	case 4:
	    float price = Helper.scanFloat("Price");
	    for (T entity : list) {
		Product product = (Product) entity;
		if (product.getPrice() == price) {
		    searchResults.add(entity);
		}
	    }
	    break;
	case 5:
	    String nameOfCategory = Helper.scanString("name of Category").trim().toLowerCase();
	    for (T entity : list) {
		Product product = (Product) entity;
		if (product.getCategory().getName().trim().toLowerCase().contains(nameOfCategory)) {
		    searchResults.add(entity);
		}
	    }
	    break;
	default:
	    System.out.println("Invalid choice. Please try again!");
	}
	return searchResults;
    }

    // VIEW - Options EDIT
    @Override
    public int showOptionsProperty() {
	int option = super.showOptionsProperty();
	System.out.println(" (" + (++option) + ") - Quantity");
	System.out.println(" (" + (++option) + ") - Price");
	System.out.println(" (" + (++option) + ") - Category");
	return option;
    }

    // EDIT
    @Override
    public boolean edit(Identifiable identifiable, int choice) {
	if (!(identifiable instanceof Product)) {
	    System.out.println("Input must be an instance of the Product class.");
	    return false;
	}
	Product product = (Product) identifiable;
	switch (choice) {
	case 3:
	    product.setQuantity(Helper.scanInteger("new Quantity"));
	    break;
	case 4:
	    product.setPrice(Helper.scanFloat("new Price"));
	    break;
	case 5:
	    int id = Helper.scanInteger("new ID");
	    Category category = new CategoryService().searchById(id);
	    product.setCategory(category);
	    break;
	default:
	    return super.edit(identifiable, choice);
	}
	return true;
    }

    // UPDATE - Quantity
    public boolean updateQuantity(List<Product> list, int id, Product newProduct) {
	int pos = getPositionById(list, id);
	int requestedQuantity = newProduct.getQuantity();
	int oldQuantity = list.get(pos).getQuantity();
	int newQuantity = oldQuantity - requestedQuantity;
	if (newQuantity < 0) {
	    System.out.println(
		    "Requested quantity is too large. Remaining quantity in stock: " + list.get(id).getQuantity());
	    return false;
	}
	Product oldProduct = list.get(pos);
	oldProduct.setQuantity(newQuantity);
	list.set(pos, oldProduct);
	return true;
    }

    // SAVE - File
    @Override
    public boolean saveFile() {
	return ProductDao.saveProduct(listOfProduct);
    }
}
