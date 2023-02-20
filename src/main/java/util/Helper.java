package main.java.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.service.DataManipulation;

public class Helper {
    // Class' variable
    public static Scanner scan = new Scanner(System.in);

    // CONFIRM message
    public static boolean confirm(String message) {
	System.out.print("> " + message + " <Y/N>: ");
	String confirm = new Scanner(System.in).nextLine().trim().toUpperCase();
	if (confirm.equals("Y")) {
	    return true;
	}
	return false;
    }

    // RESULTS
    public static void printResult(boolean result, String action) {
	System.out.println();
	if (result) {
	    System.out.println(action + " successfully!");
	    return;
	}
	System.out.println("Failed to " + action + "!");
    }

    // SELECT - Object's Property
    public static int selectProperty(DataManipulation service, String titleManage) {
	int choice = 10;
	int option = 0;
	do {
	    System.out.println("==========" + titleManage.toUpperCase() + "==========");
	    option = service.showOptionsProperty();
	    System.out.println(" (" + (++option) + ") - Back to MANAGE");
	    choice = Helper.scanInteger("your choice");
	    System.out.println();
	} while (choice > option);
	return choice;
    }

    // SCAN - Integer or String
    public static Object scanIntegerOrString(String message) {
	Object result = null;
	do {
	    System.out.print("> Enter " + message + ": ");
	    String input = scan.nextLine();
	    try {
		result = (int) Integer.parseInt(input);
	    } catch (NumberFormatException e) {
		result = (String) input;
	    } catch (Exception e) {
		System.out.println("Invalid input. Please enter an Integer or String.");
	    }
	} while (result == null);
	return result;
    }

    // SCAN - Integer
    public static Integer scanInteger(String message) {
	Integer number = null;
	do {
	    System.out.print("> Enter " + message + ": ");
	    try {
		number = scan.nextInt();
		scan.nextLine();
	    } catch (InputMismatchException e) {
		System.out.println("Invalid input. Please enter an integer.");
		scan.nextLine(); // clear the scanner buffer
	    }
	} while (number == null);
	return number;
    }

    // SCAN - String
    public static String scanString(String message) {
	System.out.print("> Enter " + message + ": ");
	return scan.nextLine();
    }

    // SCAN - Float
    public static Float scanFloat(String message) {
	Float number = null;
	do {
	    System.out.print("> Enter " + message + ": ");
	    try {
		number = scan.nextFloat();
		scan.nextLine();
	    } catch (InputMismatchException e) {
		System.out.println("Invalid input. Please enter an float.");
		scan.nextLine(); // clear the scanner buffer
	    }
	} while (number == null);
	return number;
    }

}
