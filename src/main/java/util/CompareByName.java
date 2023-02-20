package main.java.util;

import java.util.Comparator;

import main.java.model.Identifiable;

public class CompareByName implements Comparator<Identifiable> {

    @Override
    public int compare(Identifiable o1, Identifiable o2) {
	// TODO Auto-generated method stub
	return o1.getName().compareTo(o2.getName());
    }

}
