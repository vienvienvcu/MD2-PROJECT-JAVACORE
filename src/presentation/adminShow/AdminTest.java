package presentation.adminShow;

import business.constants.RoleName;
import business.entity.Users;
import business.utils.IOFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static business.feature.Impl.UserFeatureImpl.usersList;

public class AdminTest {

    public static void main(String[] args) {

        List<Users> usersList = new ArrayList<>();

        Users user = new Users();
        user.setUsersId(1);
        user.setUserName("admin66");
        user.setFullName("Admin User");
        user.setGender(false);
        user.setAddress("Hai Duong");
        user.setEmail("admin66@gmail.com");
        user.setPhone("+123456789");
        user.setRoleName(RoleName.ROLE_ADMIN);
        user.setPassword("admin66");
        user.setConfirmPassword("admin66");
        user.setCreationDate(new Date());
        user.setUpdated(new Date());
        user.setStatusUser(true);
        user.setDelete(false);

        usersList.add(user);


        IOFile.writeToFile(IOFile.PATH_USER, usersList);

        // Hiển thị thông tin người dùng
        user.displayUserData();
    }
}
