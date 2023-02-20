package main.java.util;

import java.util.Comparator;

import main.java.model.Identifiable;

public class CompareById implements Comparator<Identifiable> {

    @Override
    public int compare(Identifiable o1, Identifiable o2) {
	// TODO Auto-generated method stub
	return o1.getId() - o2.getId();
    }

}
