package uz.pdp.online.TestManagement.service.admin;

import uz.pdp.online.TestManagement.entity.Question;
import uz.pdp.online.TestManagement.entity.Users;
import uz.pdp.online.TestManagement.service.admin.action.QuestionService;
import uz.pdp.online.TestManagement.service.admin.action.SubjectService;
import uz.pdp.online.TestManagement.service.admin.action.UserServiceAction;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminService {
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);
    static SubjectService subjectService=new SubjectService();
    static QuestionService questionService=new QuestionService();
    static UserServiceAction userServiceAction=new UserServiceAction();
    public void adminMenyu(Users user) throws SQLException {
        while (true){
            System.out.println("1.Subject_CRUD");
            System.out.println("2.Question_CRUD");
            System.out.println("3.UserAction");
            System.out.println("0.Back");
            System.out.print("Select:");
            int option=SCANNER_NUM.nextInt();
            switch (option){
                case 1:subjectService.SubjectCRUD();break;
                case 2:questionService.questionCRUD();break;
                case 3:userServiceAction.divideUser();break;
                case 0: return;
            }

        }
    }
}
