package uz.pdp.online.TestManagement;

import uz.pdp.online.TestManagement.service.AuthService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static AuthService authService=new AuthService();
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        boolean Active=true;
        while (Active){
            System.out.println("************** Welcome to TestManagement ******************");
            System.out.println("Mention😉: if you are a User,press button 1,else press button 2");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("0.Exit");
            System.out.print("Select:");
            int option= SCANNER_NUM.nextInt();
            switch (option){
                case 1:
                    authService.login();
                    break;

                case 2:
                    authService.register();
                    break;

                case 0:
                    Active=false;
                    break;

            }
        }

    }
}
