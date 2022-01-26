package uz.pdp.online.TestManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.online.TestManagement.utils.UserRole;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    private Integer id;
    private String name;
    private String userName;
    private String password;
    private UserRole role=UserRole.USER;
    private String phone;
    private boolean active;
    private boolean isDeleted;


    public Users( String phone,String password, UserRole role) {
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public Users(String userName, String phone, String password, UserRole role) {
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }
}
