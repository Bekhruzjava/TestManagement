package uz.pdp.online.TestManagement.service.admin.action;

import java.util.Scanner;

public class UserServiceAction {
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);

    public void divideUser() {
      while (true){
          System.out.println("1.UerList");
          System.out.println("2.UserByQuestion");
          System.out.println("3.UserByAnswer");
          System.out.println("4.UserListPDF");
          System.out.println("5.QuestionListPDF");
          System.out.println("0.Back");
          System.out.println("Select:");
          int option=SCANNER_NUM.nextInt();
          switch (option){


          }
      }
    }
}
