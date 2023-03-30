package ra.bussiness.impl;

import ra.bussiness.design.Icrud;
import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.User;
import ra.data.DataURL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserImp implements Icrud<User,Integer> {
    @Override
    public boolean create(User user) {
        List<User> listUser = readFromFile();
        if (listUser==null){
            listUser= new ArrayList<>();
        }
        listUser.add(user);
        boolean check = writeToFile(listUser);
        return check;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public List<User> readFromFile() {
        List<User> listUser = null;
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_USER_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listUser = (List<User>) ois.readObject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return listUser;
    }

    @Override
    public boolean writeToFile(List<User> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_USER_FILE);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (Exception ex) {
            returnData = false;
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return returnData;
    }

    @Override
    public User inputData(Scanner sc) {
        return null;
    }

    @Override
    public void displayData() {

    }

    public User checkLogin(String userName, String password) {
        List<User> listUser = readFromFile();
        for (User user : listUser) {
            if (user.getUserName().equals(userName)&&user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
