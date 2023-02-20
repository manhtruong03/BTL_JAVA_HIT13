package main.java.controller;

import java.util.List;

import main.java.model.Product;
import main.java.service.ProductService;

public class ProductController {
    // Object's property
    private ProductService productService;

    // Constructor
    public ProductController() {
	super();
	this.productService = new ProductService();
    }

    public ProductController(ProductService productService) {
	super();
	this.productService = productService;
    }

    // Getter and Setter
    public ProductService getProductService() {
	return productService;
    }

    public void setProductService(ProductService productService) {
	this.productService = productService;
    }

    // MANAGE - Product
    public void manageProducts() {
	List<Product> listOfProduct = productService.getListOfProduct();
	HomeController.manage(listOfProduct, productService, "Product");
    }

    // Billing point
    public void billingPoint() {
	List<Product> listOfProduct = productService.getListOfProduct();
	HomeController.sellerController(listOfProduct, "Product");
    }
}
