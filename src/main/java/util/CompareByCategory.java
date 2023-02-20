package main.java.util;

import java.util.Comparator;

import main.java.model.Identifiable;
import main.java.model.Product;

public class CompareByCategory<T extends Identifiable> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
	Product product1 = (Product) o1;
	Product product2 = (Product) o2;
	return product1.getCategory().getName().compareTo(product1.getCategory().getName());
    }

}
