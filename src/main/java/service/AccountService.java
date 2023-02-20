package main.java.service;

import java.util.Collections;
import java.util.List;

import main.java.dao.AccountDao;
import main.java.model.AccessLevel;
import main.java.model.Account;
import main.java.model.Identifiable;
import main.java.model.Person;
import main.java.util.CompareByAccessLevel;
import main.java.util.CompareByPassword;
import main.java.util.CompareByUsername;
import main.java.util.Helper;

public class AccountService extends PersonService {
    // Object's property
    private List<Account> listOfAccount;

    // Constructor
    public AccountService() {
	super();
	this.listOfAccount = AccountDao.loadAccounts();
    }

    public AccountService(List<Account> listOfAccount) {
	super();
	this.listOfAccount = listOfAccount;
    }

    // Getter and Setter
    public <T extends Identifiable> List<T> getListOfAccount() {
	return (List<T>) listOfAccount;
    }

    public void setListOfAccount(List<Account> listOfAccount) {
	this.listOfAccount = listOfAccount;
    }

    // SORT
    @Override
    public boolean sort(List<? extends Identifiable> list, int choice) {
	switch (choice) {
	case 6:
	    Collections.sort(list, new CompareByUsername<Identifiable>());
	    break;
	case 7:
	    Collections.sort(list, new CompareByPassword<Identifiable>());
	    break;
	case 8:
	    Collections.sort(list, new CompareByAccessLevel<Identifiable>());
	    break;
	default:
	    super.sort(list, choice);
	}
	return true;
    }

    // SEARCH
    @Override
    public <T extends Identifiable> List<T> search(List<T> list, int choice) {
	if (!(list.get(0) instanceof Account)) {
	    return null;
	}
	List<T> searchResults = super.search(list, choice);
	switch (choice) {
	case 6:
	    String username = Helper.scanString("Username");
	    for (T entity : list) {
		Account account = (Account) entity;
		if (account.getUsername().equals(username)) {
		    searchResults.add(entity);
		}
	    }
	    break;
	case 7:
	    String password = Helper.scanString("Password");
	    for (T entity : list) {
		Account account = (Account) entity;
		if (account.getPassword().equals(password)) {
		    searchResults.add(entity);
		}
	    }
	    break;
	case 8:
	    AccessLevel accessLevel = new AccessLevel(Helper.scanIntegerOrString("AccessLevel"));
	    for (T entity : list) {
		Account account = (Account) entity;
		if (account.getAccessLevel().equals(accessLevel)) {
		    searchResults.add(entity);
		}
	    }
	    break;
	default:
	    System.out.println("Invalid choice. Please try again!");
	}
	return searchResults;
    }

    // VIEW title
    @Override
    public String title() {
	return super.title() + String.format("%-15s %-15s %-15s ", "USERNAME", "PASSWORD", "ACCESS_LEVEL");
    }

    // CREATE a Account
    @Override
    public Identifiable create() {
	Person person = (Person) super.create();
	String username = Helper.scanString("username");
	String password = Helper.scanString("password");
	AccessLevel.viewHashTable();
	AccessLevel accessLevel = new AccessLevel(Helper.scanIntegerOrString("Access Level"));
	return new Account(person, username, password, accessLevel);
    }

    // VIEW - Options EDIT
    @Override
    public int showOptionsProperty() {
	int option = super.showOptionsProperty();
	System.out.println(" (" + (++option) + ") - Username");
	System.out.println(" (" + (++option) + ") - Password");
	System.out.println(" (" + (++option) + ") - Access Level");
	return option;
    }

    // EDIT
    @Override
    public boolean edit(Identifiable identifiable, int choice) {
	if (!(identifiable instanceof Account)) {
	    System.out.println("Input must be an instance of the Account class.");
	    return false;
	}
	Account account = (Account) identifiable;
	switch (choice) {
	case 6:
	    account.setUsername(Helper.scanString("new Username"));
	    break;
	case 7:
	    account.setPassword(Helper.scanString("new Password"));
	    break;
	case 8:
	    AccessLevel.viewHashTable();
	    account.getAccessLevel().setAccessLevel(Helper.scanIntegerOrString("new Access Level"));
	    break;
	default:
	    return super.edit(identifiable, choice);
	}
	return true;
    }

    // SAVE - File
    @Override
    public boolean saveFile() {
	return AccountDao.saveAccounts(listOfAccount);
    }

    // VERIFY - AccessLevel
    public int verifyAccess(String username, String password) {
	for (Account acc : listOfAccount) {
	    if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
		return acc.getAccessLevel().getValueLevel();
	    }
	}
	return AccessLevel.NONE;
    }

    // CHANGE - Password
    public boolean changePassword(String username, String password) {
	for (Account acc : listOfAccount) {
	    if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
		edit(acc, 7);
		return saveFile();
	    }
	}
	System.out.println("Sorry, the account does not exist!");
	return false;
    }

    // FORGOT - Password
    public void forgotPassword(String username, String phoneNumber) {
	for (Account acc : listOfAccount) {
	    if (acc.getUsername().equals(username) && acc.getPhoneNumber().equals(phoneNumber)) {
		int verifyCode = (int) (Math.random() * 100_000);
		System.out.println("Your password reset confirmation code is: " + verifyCode);
		if (verifyCode == Helper.scanInteger("the verification code sent to your phone number")) {
		    System.out.println("Your password is: " + acc.getPassword());
		    return;
		} else {
		    System.out.println("Sorry, the verification code is incorrect!");
		}
	    }
	}
	System.out.println("Sorry, the account does not exist!");
    }

}
