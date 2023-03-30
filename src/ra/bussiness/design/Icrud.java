package ra.bussiness.design;

import java.util.List;
import java.util.Scanner;

public interface Icrud<T,E> {
    boolean create(T t);
    boolean update(T t);
    boolean delete(E id);
    List<T> findAll();
    T findById(E id);
    List<T> readFromFile();
    boolean writeToFile(List<T> list);
    T inputData(Scanner sc);
    void displayData();
}
