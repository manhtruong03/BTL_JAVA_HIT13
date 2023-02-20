package main.java.controller;

import java.util.List;
import java.util.Scanner;

import main.java.model.Account;
import main.java.service.AccountService;
import main.java.util.Helper;

public class AccountController {
    // Object's property
    private AccountService accountService;

    // Constructor
    public AccountController() {
	super();
	this.accountService = new AccountService();
    }

    public AccountController(AccountService accountService) {
	super();
	this.accountService = accountService;
    }

    // Getter and Setter
    public AccountService getAccountService() {
	return accountService;
    }

    public void setAccountService(AccountService accountService) {
	this.accountService = accountService;
    }

    // LOGIN
    public void loginMenu() {
	Scanner scan = new Scanner(System.in);
	System.out.println("==========LOGIN==========");
	System.out.print("> Username: ");
	String username = scan.nextLine();
	System.out.print("> Password: ");
	String password = scan.nextLine();
	System.out.println();
	int accessLevel = -1;
	accessLevel = accountService.verifyAccess(username, password);
	if (accessLevel == 0) {
	    System.out.println("Login failed! Incorrect username or password.");
	    return;
	}
	if (accessLevel >= 3) {
	    if (Helper.confirm("Login as Administrator?")) {
		HomeController.adminController();
		return;
	    }
	}
	if (accessLevel >= 2) {
	    new ProductController().billingPoint();
	    return;
	}
	System.out.println("Sorry, your account does not have access rights!");
    }

    // MANAGE - Accounts
    public void manageAccounts() {
	List<Account> listOfAccount = accountService.getListOfAccount();
	HomeController.manage(listOfAccount, accountService, "Account");
    }

    // FORGOT - Password
    public void forgotPassword() {
	System.out.println("==========FORGOT PASSWORD==========");
	String username = Helper.scanString("username");
	String phoneNumber = Helper.scanString("phoneNumber");
	accountService.forgotPassword(username, phoneNumber);
    }

    // CHANGE - Password
    public void changePassword() {
	System.out.println("==========CHANGE PASSWORD==========");
	String username = Helper.scanString("username");
	String password = Helper.scanString("password");
	Helper.printResult(accountService.changePassword(username, password), "Change Password");
    }
}