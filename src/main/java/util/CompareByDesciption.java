package main.java.util;

import java.util.Comparator;

import main.java.model.Category;
import main.java.model.Identifiable;

public class CompareByDesciption<T extends Identifiable> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
	// TODO Auto-generated method stub
	Category category1 = (Category) o1;
	Category category2 = (Category) o2;
	return category1.getDescription().compareTo(category2.getDescription());
    }

}
