package presentation.adminShow;

import business.constants.Role;
import business.entity.Users;
import business.utils.IOFile;

import static business.feature.Impl.UserFeatureImpl.usersList;

public class AdminTest {

    public static void main(String[] args) {
        Users user = new Users();
        user.setUserName("admin66");
        user.setPassword("admin66");
        user.setAddress("hai duong");
        user.setConfirmPassword("admin66");
        user.setEmail("admin66@gmail.com");
        user.setPhone("+123456789");
        user.setRoleName(Role.ROLE_ADMIN);
        usersList.add(user);
        // cập nhật lại list users trong file
        IOFile.writeToFile(IOFile.PATH_USER, usersList);
    }
}
