package ra.presentation;

import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.User;
import ra.bussiness.impl.CatalogImp;
import ra.bussiness.impl.UserImp;
import ra.bussiness.notify.ShopMessage;

import java.util.Scanner;

public class ShopManagement {
    private static CatalogImp catImp = new CatalogImp();
    private static UserImp userImp = new UserImp();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("****************CUA HANG ABC***************");
            System.out.println("1. Dang nhap");
            System.out.println("2. Dang ky");
            System.out.println("3. Thoat");
            System.out.print("Su lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    login(sc);
                    break;
                case 2:
                    break;
                case 3:
                    sc.close();
                    System.exit(0);
                default:
                    System.err.println(ShopMessage.NOTIFY_SHOP_CHOICE);
            }
        }while(true);
    }
    public static void login(Scanner sc){
        do {
            System.out.print("Ten dang nhap: ");
            String userName = sc.nextLine();
            System.out.print("Mat khau: ");
            String password = sc.nextLine();
            User user = userImp.checkLogin(userName,password);
            if (user!=null){
                //Dang nhap thanh cong
                if (user.isPermission()){
                    //Tai khoan admin
                    displayMenuShopManagement(sc);
                }else{
                    //Tai khoan user
                    displauMenuUser(sc);
                }
                break;
            }else{
                //Dang nhap that bai
                System.err.println(ShopMessage.NOTIFY_LOGIN_FAIL);
                System.out.println("1. Dang nhap lai");
                System.out.println("2. Dang ky tai khoan");
                System.out.println("3. Thoat");
                System.out.print("Su lua chon: ");
                int choice = Integer.parseInt(sc.nextLine());
                if (choice==2){
                    //Chuyen den menu dang ky
                }else if(choice==3){
                    break;
                }
            }
        }while (true);
    }

    public static void displayMenuShopManagement(Scanner sc){
        boolean exit = true;
        do {
            System.out.println("**********************QUAN LY CUA HANG************************");
            System.out.println("1. Quan ly danh muc");
            System.out.println("2. Quan ly san pham");
            System.out.println("3. Quan ly nguoi dung");
            System.out.println("4. Thoat");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    displayMenuCatalogManagement(sc);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    exit = false;
                    break;
                default:
                    System.err.println(ShopMessage.NOTIFY_SHOP_MANAGEMENT_CHOICE);
            }
        }while(exit);

    }

    public static void displauMenuUser(Scanner sc){

    }

    public static void displayMenuCatalogManagement(Scanner sc){
        boolean exit = true;
        do {
            System.out.println("**************QUAN LY DANH MUC**********************");
            System.out.println("1. Danh sach danh muc");
            System.out.println("2. Them moi danh muc");
            System.out.println("3. Cap nhat danh muc");
            System.out.println("4. Xoa danh muc");
            System.out.println("5. Tim danh muc theo ten");
            System.out.println("6. Thoat");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    catImp.displayData();
                    break;
                case 2:
                    Catalog catNew = catImp.inputData(sc);
                    boolean result = catImp.create(catNew);
                    if (result){
                        System.out.println(ShopMessage.NOTIFY_CATALOG_CREATE_SUCCESS);
                    }else{
                        System.err.println(ShopMessage.NOTIFY_CATALOG_CREATE_FAIL);
                    }
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    exit = false;
                    break;
                default:
                    System.err.println(ShopMessage.NOTIFY_CATALOG_MANAGEMENT_CHOICE);
            }
        }while(exit);
    }
}
