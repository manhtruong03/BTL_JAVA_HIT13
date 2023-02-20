package main.java.model;

public class Product extends Identifiable {
    // Object's property
    private int quantity; // So luong san pham
    private float price; // Gia san pham
    private Category category; // Loai danh muc cua san pham

    // Constructor
    public Product() {
	super();
    }

    public Product(int id, String name, int quantity, float price, Category category) {
	super(id, name);
	this.quantity = quantity;
	this.price = price;
	this.category = category;
    }

    public Product(Identifiable identifiable, int quantity, float price, Category category) {
	super(identifiable);
	this.quantity = quantity;
	this.price = price;
	this.category = category;
    }

    public Product(Product product) {
	super(product.getId(), product.getName());
	this.quantity = product.getQuantity();
	this.price = product.getPrice();
	this.category = product.getCategory();
    }

    // Getters and Setters
    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public float getPrice() {
	return price;
    }

    public void setPrice(float price) {
	this.price = price;
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    @Override
    public String toString() {
	return super.toString() + String.format("%-15d %-15.2f %-25s ", quantity, price, category.getName());
    }

    public float getTotal() {
	return quantity * price;
    }
}
