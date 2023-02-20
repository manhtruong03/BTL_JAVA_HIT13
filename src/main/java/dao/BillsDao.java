package main.java.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BillsDao {
    public static boolean printBill(List<String> billLines) {
	try {
	    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("data/Bills.txt", true)));
	    writer.print("\n\n=======================================================================\n\n");
	    writer.print("\t\t\t\tMANH MEW MEW MART\n\n");
	    for (String line : billLines) {
		writer.println(line);
	    }
	    writer.close();
	    return true;
	} catch (IOException e) {
	    System.out.println("An error occurred while saving categories.");
	    return false;
	}
    }
}
