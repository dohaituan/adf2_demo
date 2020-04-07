package adf2;

import adf2.entity.Account;
import adf2.helper.ConnectionHelper;
import adf2.model.AccountModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MainThread {

    private AccountModel model = new AccountModel();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws SQLException {
        AccountModel model = new AccountModel();
        model.test();
        model.save(new Account("tuanbun","1234","Hai Tuan","2000-12-08"));
        seeding();
        // Menu
        // 1. Danh sách accounts.
        // 2. Xem account chi tiết -> Yêu cầu người dùng nhập username và password
        // 2.1. Nếu có username và password trùng thì hiển thị thông tin đầy đủ của account.
        // 2.2. Nếu không có thì báo "không tìm thấy account"
        // 3. Thoát.
        // Nhập lựa chọn của bạn.

        while (true) {
            System.out.println("------------Menu-----------");
            System.out.println("1. Danh sách account.");
            System.out.println("2. Xem chi tiết");
            System.out.println("3. Thoát chương trình");
            System.out.println("-------------------------------------");
            System.out.print("Vui lòng nhập lựa chọn của bạn (1 đến 3) : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ArrayList<Account> list = model.getList();
                    printList(list);
                    break;
                case 2:
                    printAccount();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.print("Vui lòng lựa chọn trong khoảng từ 1 đến 3.");
                    break;
            }
            if (choice == 3) {
                break;
            }
        }
    }



    public static void seeding(){
        AccountModel model = new AccountModel();
        Account account = new Account("haituan","1234","Hai Tuan","2000-12-08");
        model.save(account);
        account = new Account("xuanhung","0123","Xuan Hung","2001-05-23");
        model.save(account);
        account = new Account("vanbang","4630","Van Bang","1995-01-04");
        model.save(account);
        account = new Account("hathanh","1120","Ha Thanh","2000-05-19");
        model.save(account);
        account = new Account("congmanh","5122","Cong Manh","1999-10-10");
        model.save(account);
        account = new Account("trunghau","0323","Trung Hau","2001-08-15");
        model.save(account);
        account = new Account("hoanghuy","6612","Hoang Huy","2001-08-27");
        model.save(account);
        account = new Account("vandat","1456","Van Dat","2001-09-23");
        model.save(account);
        account = new Account("thanhtung","0213","Thanh Tung","2001-05-28");
        model.save(account);
        account = new Account("hakhoa","5122","Ha Khoa","2001-05-23");
        model.save(account);
        }

    public static void printList(ArrayList<Account> listAccount) {
        if (listAccount.size() == 0) {
            System.out.println("Hiện tại không có account trong danh sách.");
            return;
        }
        System.out.println("------------------------------------------------------------Danh sách account---------------------------------------------------------");
        System.out.printf("%5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s\n"
                , "", "UserName", "", "|"
                , "", "Password", "", "|"
                , "", "FullName", "", "|"
                , "", "CreatedDate", "");
        for (int i = 0; i < listAccount.size(); i++) {
            System.out.printf("%5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s\n"
                    , "", listAccount.get(i).getUsername(), "", "|"
                    , "", listAccount.get(i).getPassword(), "", "|"
                    , "", listAccount.get(i).getFullName(), "", "|"
                    , "", listAccount.get(i).getCreatedDate(), "");
        }
    }
    public static void printAccount() {
        Account account = new Account();
        System.out.print("Vui lòng nhập username: ");
        account.setUsername(scanner.nextLine());
        System.out.print("Vui lòng nhập password: ");
        account.setPassword(scanner.nextLine());
        Account acc = AccountModel.getAccout(account.getUsername(),(account.getPassword()));
        if (account.getUsername() == null | account.getPassword() == null) {
            System.out.println("Tài khoản hoặc mật khẩu không tồn tại !");
        } else {
            System.out.println("------------------------------------------------------------Account Information---------------------------------------------------------");
            System.out.printf("%5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s\n"
                    , "", "UserName", "", "|"
                    , "", "Password", "", "|"
                    , "", "FullName", "", "|"
                    , "", "CreatedDate", "");
        }
        System.out.printf("%5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s\n"
                , "", acc.getUsername(), "", "|"
                , "", acc.getPassword(), "", "|"
                , "", acc.getFullName(), "", "|"
                , "", acc.getCreatedDate(), "");
    }
}

