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

import main.java.model.Account;
import main.java.model.Person;

public class AccountDao {
    public static boolean saveAccounts(List<Account> listOfAccount) {
	PrintWriter writer;
	try {
	    writer = new PrintWriter(new BufferedWriter(new FileWriter("data/Accounts.txt")));
	    for (Account account : listOfAccount) {
		writer.print(account.getId() + "|");
		writer.print(account.getName() + "|");
		writer.print(account.getAge() + "|");
		writer.print(account.getPhoneNumber() + "|");
		writer.print(account.getAddress() + "|");

		writer.print(account.getUsername() + "|");
		writer.print(account.getPassword() + "|");
		writer.print(account.getAccessLevel().getNameLevel() + "\n");
	    }
	    writer.close();
	    return true;
	} catch (IOException e) {
	    System.out.println("An error occurred while saving accounts.");
	    e.printStackTrace();
	    return false;
	}
    }

    public static List<Account> loadAccounts() {
	List<Account> listOfAccount = new ArrayList<>();
	try (BufferedReader reader = new BufferedReader(new FileReader("data/Accounts.txt"))) {
	    String line = reader.readLine();
	    while (line != null) {
		String[] parts = line.split("\\|");
		int id = Integer.parseInt(parts[0]);
		int age = Integer.parseInt(parts[2]);
		Person person = new Person(id, parts[1], age, parts[3], parts[4]);
		Account account = new Account(person, parts[5], parts[6], parts[7]);
		listOfAccount.add(account);
		line = reader.readLine();
	    }
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
	return listOfAccount;
    }
}
