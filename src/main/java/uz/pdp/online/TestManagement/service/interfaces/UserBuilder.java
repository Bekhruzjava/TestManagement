package uz.pdp.online.TestManagement.service.interfaces;

import uz.pdp.online.TestManagement.entity.Users;
import uz.pdp.online.TestManagement.utils.UserRole;

public interface UserBuilder {
    Users build( String username,String phone, String password, UserRole role);
}
