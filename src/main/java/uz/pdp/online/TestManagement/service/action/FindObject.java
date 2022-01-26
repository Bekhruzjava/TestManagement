package uz.pdp.online.TestManagement.service.action;

import uz.pdp.online.TestManagement.entity.Users;
import uz.pdp.online.TestManagement.repository.Database;

public class FindObject {
    public Users checkUser(String username){
        return Database.usersList.stream().filter(user -> user.getUserName().equals(username)).findFirst().orElse(null);
    }
}
