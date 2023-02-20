package main.java.controller;

import java.util.ArrayList;
import java.util.List;

import main.java.dao.BillsDao;
import main.java.dao.ProductDao;
import main.java.model.Identifiable;
import main.java.model.Product;
import main.java.service.DataManipulation;
import main.java.service.ProductService;
import main.java.util.Helper;

public class HomeController {

    // WELCOME
    public static void welcomeMenu() {
	System.out.println("===================================");
	System.out.println("| SUPER MARKET MANAGEMENT SYSTEM |");
	System.out.println("===================================");
	System.out.println("");

	AccountController accountController = new AccountController();
	while (true) {
	    System.out.println("\t\t\t\tMENU");
	    System.out.println(" (1) - Login");
	    System.out.println(" (2) - Forgot Password");
	    System.out.println(" (3) - Change Password");
	    int choice = Helper.scanInteger("your choice");
	    System.out.println();

	    switch (choice) {
	    case 1:
		accountController.loginMenu();
		break;
	    case 2:
		accountController.forgotPassword();
		break;
	    case 3:
		accountController.changePassword();
		break;
	    default:
		System.out.println("Invalid choice. Please try again!");
	    }
	}
    }

    // ADMIN
    public static void adminController() {
	System.out.println("Logged in successfully!");
	while (true) {
	    System.out.println("==========ADMIN==========");
	    System.out.println(" (1) - Manage Products");
	    System.out.println(" (2) - Manage Categories");
	    System.out.println(" (3) - Manage Account");
	    System.out.println(" (4) - Log Out");
	    int choice = Helper.scanInteger("your choice");
	    System.out.println();
	    switch (choice) {
	    case 1:
		new ProductController().manageProducts();
		break;
	    case 2:
		new CategoryController().manageCategorys();
		break;
	    case 3:
		new AccountController().manageAccounts();
		break;
	    case 4:
		System.out.println("You have been successfully logged out.");
		return;
	    default:
		System.out.println("Invalid choice. Please try again!");
	    }
	}
    }

    // SELLER
    public static void sellerController(List<Product> listOfProduct, String nameObject) {
	System.out.println("Logged in successfully!");
	List<Product> bill = new ArrayList<>();
	ProductService service = new ProductService(bill);
	while (true) {
	    System.out.println("==========BILLING POINT==========");
	    service.view(service.billLines(), "");
	    System.out.println("============SELLER=============");
	    System.out.println(" (0) - VIEW List Product");
	    System.out.println(" (1) - SORT List Product");
	    System.out.println(" (2) - SEARCH Product");
	    System.out.println(" (3) - ADD to Bill");
	    System.out.println(" (4) - EDIT Bill");
	    System.out.println(" (5) - DELETE Product form Bill");
	    System.out.println(" (6) - PRINT");
	    System.out.println(" (7) - SAVE the changes");
	    System.out.println(" (8) - Refresh list of Product");
	    System.out.println(" (9) - Log Out");
	    int choice = Helper.scanInteger("your choice");
	    System.out.println();
	    switch (choice) {
	    case 0:
		service.view(listOfProduct, service.title());
		break;
	    case 1:
		Helper.printResult(HomeController.sort(service, listOfProduct, nameObject), "SORT");
		break;
	    case 2:
		HomeController.search(service, listOfProduct, nameObject);
		break;
	    case 3:
		int idToAdd = Helper.scanInteger("ID of Product");
		Product product = new Product((Product) service.searchById(listOfProduct, idToAdd));
		service.edit(product, 3);
		service.updateQuantity(listOfProduct, idToAdd, product);
		Helper.printResult(service.add(bill, product), "ADD " + nameObject);
		break;
	    case 4:
		int id = Helper.scanInteger("ID of Product");
		if (service.checkIdExist(listOfProduct, id)) {
		    Product entity = (Product) service.searchById(listOfProduct, id);
		    service.view(entity, service.title());
		    HomeController.edit(service, entity, nameObject);
		}
		break;
	    case 5:
		Helper.printResult(service.remove(bill, Helper.scanInteger("ID")), "DELETE " + nameObject);
		break;
	    case 6:
		if (Helper.confirm("Do you want to continue?")) {
		    Helper.printResult(BillsDao.printBill(service.billLines()), "PRINT Billing Point");

		}
		break;
	    case 7:
		if (Helper.confirm("Do you want to SAVE the changes?")) {
		    Helper.printResult(ProductDao.saveProduct(listOfProduct), "SAVE changes");
		}
		break;
	    case 8:
		listOfProduct = ProductDao.loadProduct();
		break;
	    case 9:
		System.out.println("You have been successfully logged out.");
		return;
	    default:
		System.out.println("Invalid choice. Please try again!");
	    }
	}
    }

    // MANAGE
    public static <Type extends Identifiable> void manage(List<Type> list, DataManipulation service,
	    String nameObject) {
	while (true) {
	    System.out.println("==========MANAGE " + nameObject.toUpperCase() + "==========");
	    System.out.println(" (0) - VIEW");
	    System.out.println(" (1) - SORT");
	    System.out.println(" (2) - SEARCH");
	    System.out.println(" (3) - ADD");
	    System.out.println(" (4) - EDIT");
	    System.out.println(" (5) - DELETE");
	    System.out.println(" (6) - SAVE and EXIT");
	    int choice = Helper.scanInteger("your choice");
	    System.out.println();
	    switch (choice) {
	    case 0:
		service.view(list, service.title());
		break;
	    case 1:
		Helper.printResult(HomeController.sort(service, list, nameObject), "SORT");
		break;
	    case 2:
		HomeController.search(service, list, nameObject);
		break;
	    case 3:
		Type identifiable = (Type) service.create();
		Helper.printResult(service.add(list, identifiable), "ADD " + nameObject);
		break;
	    case 4:
		service.view(list, service.title());
		int oldID = Helper.scanInteger("old ID");
		if (service.checkIdExist(list, oldID)) {
		    Identifiable entity = service.searchById(list, oldID);
		    service.view(entity, service.title());
		    HomeController.edit(service, entity, nameObject);
		}
		break;
	    case 5:
		Helper.printResult(service.remove(list, Helper.scanInteger("ID")), "DELETE " + nameObject);
		break;
	    case 6:
		if (Helper.confirm("Do you want to SAVE the changes?")) {
		    Helper.printResult(service.saveFile(), "SAVE changes");
		}
		return;
	    default:
		System.out.println("Invalid choice. Please try again!");
	    }
	}
    }

    // SORT
    public static <T extends Identifiable> boolean sort(DataManipulation service, List<T> list, String titleManage) {
	int choice = Helper.selectProperty(service, "SORT " + titleManage + " BY");
	return service.sort(list, choice);
    }

    // SEARCH
    public static <T extends Identifiable> void search(DataManipulation service, List<T> list, String titleManage) {
	HomeController.sort(service, list, titleManage);
	int choice = Helper.selectProperty(service, "SEARCH " + titleManage + " BY");
	List<T> searchResults = service.search(list, choice);
	service.view(searchResults, service.title());
    }

    // EDIT
    public static boolean edit(DataManipulation service, Identifiable identifiable, String titleManage) {
	int choice = Helper.selectProperty(service, "EDIT " + titleManage + " BY");
	return service.edit(identifiable, choice);
    }

}
