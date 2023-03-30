package ra.bussiness.impl;

import ra.bussiness.design.Icrud;
import ra.bussiness.entity.Catalog;
import ra.bussiness.notify.ShopMessage;
import ra.data.DataURL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalogImp implements Icrud<Catalog, Integer> {
    @Override
    public boolean create(Catalog catalog) {
        List<Catalog> listCatalog = readFromFile();
        if (listCatalog==null){
            listCatalog = new ArrayList<>();
        }
        listCatalog.add(catalog);
        boolean result = writeToFile(listCatalog);
        return result;
    }

    @Override
    public boolean update(Catalog catalog) {
        List<Catalog> listCatalog = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listCatalog.size(); i++) {
            if (listCatalog.get(i).getCatalogId() == catalog.getCatalogId()) {
                //Thuc hien cap nhat
                listCatalog.set(i, catalog);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listCatalog);
        if (result && returnData) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        List<Catalog> listCatalog = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listCatalog.size(); i++) {
            if (listCatalog.get(i).getCatalogId() == id) {
                listCatalog.remove(i);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listCatalog);
        if (result && returnData) {
            return true;
        }
        return false;
    }

    @Override
    public List<Catalog> findAll() {
        return readFromFile();
    }

    @Override
    public Catalog findById(Integer id) {
        List<Catalog> listCatalog = readFromFile();
        for (Catalog cat : listCatalog) {
            if (cat.getCatalogId() == id) {
                return cat;
            }
        }
        return null;
    }

    @Override
    public List<Catalog> readFromFile() {
        List<Catalog> listCatalog = null;
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_CATALOG_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listCatalog = (List<Catalog>) ois.readObject();
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
        return listCatalog;
    }

    @Override
    public boolean writeToFile(List<Catalog> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_CATALOG_FILE);
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
    public Catalog inputData(Scanner sc) {
        List<Catalog> listCatalog = readFromFile();
        if (listCatalog==null){
            listCatalog = new ArrayList<>();
        }
        //Khoi tao doi tuong de nhap thong tin
        Catalog catNew = new Catalog();
        System.out.println("Nhap vao ma danh muc: ");
        do {
            catNew.setCatalogId(Integer.parseInt(sc.nextLine()));
            boolean checkExist = false;
            for (Catalog cat : listCatalog) {
                if (cat.getCatalogId() == catNew.getCatalogId()) {
                    checkExist = true;
                    break;
                }
            }
            if (!checkExist) {
                break;
            } else {
                System.err.println(ShopMessage.NOTIFY_CATALOGID_EXIST);
            }
        } while (true);
        System.out.println("Nhap vao ten danh muc: ");
        catNew.setCatalogName(sc.nextLine());
        System.out.println("Nhap vao trang thai danh muc: ");
        catNew.setCatalogStatus(Boolean.parseBoolean(sc.nextLine()));
        return catNew;
    }

    @Override
    public void displayData() {
        List<Catalog> listCatalog = readFromFile();
        for (Catalog cat : listCatalog) {
            System.out.printf("Ma DM: %d - Ten DM: %s - Trang thai: %b\n", cat.getCatalogId(),cat.getCatalogName(),cat.isCatalogStatus());
        }
    }

    public List<Catalog> findCatalogByName(String catalogName) {
        List<Catalog> listCatalogFull = readFromFile();
        List<Catalog> listCatalog = new ArrayList<>();
        for (Catalog cat : listCatalogFull) {
            if (cat.getCatalogName().contains(catalogName)) {
                listCatalog.add(cat);
            }
        }
        return listCatalog;
    }
}
