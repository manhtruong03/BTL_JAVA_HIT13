package main.java.util;

import java.util.Comparator;

import main.java.model.Account;
import main.java.model.Identifiable;

public class CompareByAccessLevel<T extends Identifiable> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
	Account account1 = (Account) o1;
	Account account2 = (Account) o2;
	return account1.getAccessLevel().getValueLevel() - account2.getAccessLevel().getValueLevel();
    }

}
