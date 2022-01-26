package uz.pdp.online.TestManagement.service;

import uz.pdp.online.TestManagement.entity.Users;
import uz.pdp.online.TestManagement.repository.Database;
import uz.pdp.online.TestManagement.service.action.FindObject;
import uz.pdp.online.TestManagement.service.admin.AdminService;
import uz.pdp.online.TestManagement.service.interfaces.UserBuilder;
import uz.pdp.online.TestManagement.service.user.UserService;
import uz.pdp.online.TestManagement.utils.UserRole;

import java.sql.SQLException;
import java.util.Scanner;

import static uz.pdp.online.TestManagement.entity.Message.CREATED;
import static uz.pdp.online.TestManagement.entity.Message.USERNAME_EXIST;

public class AuthService {
    public static final Scanner SCANNER_STR = new Scanner(System.in);
    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    static FindObject findObject = new FindObject();
    static AdminService adminService = new AdminService();
    static UserService userService = new UserService();

    public void login() throws SQLException {
        System.out.print("Enter username: ");
        String username = SCANNER_STR.nextLine();
        System.out.print("Enter password: ");
        String password = SCANNER_STR.nextLine();

        Users user = Database.usersList.stream().filter(user1 -> user1.getUserName().equals(username) && user1.getPassword().equals(password)).findFirst().orElse(null);
        if (user != null) {
            switch (user.getRole()) {
                case ADMIN: {
                    adminService.adminMenyu(user);
                    break;
                }
                case USER: {
                    userService.userMenyu(user);
                    break;
                }
            }
        } else {
            System.out.println("Login yoki parol xato 😒");
        }
    }

    public void register() {
        System.out.println("Enter username: ");
        String username = SCANNER_STR.nextLine();
        System.out.println("Enter password: ");
        String password = SCANNER_STR.nextLine();
        System.out.println("Enter phoneNumber:");
        String phone = SCANNER_STR.nextLine();
        if (!phone.startsWith("+998")) {
            System.out.println("This phoneNumber don't");
        } else {
            Users user = findObject.checkUser(username);
            if (user != null) {
                System.out.println(USERNAME_EXIST);
                return;
            }
            UserBuilder userBuilder = Users::new;
            Database.usersList.add(userBuilder.build(username, phone, password, UserRole.USER));
            System.out.println(CREATED);
        }
    }
}
