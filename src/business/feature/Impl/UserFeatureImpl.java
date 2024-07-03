package business.feature.Impl;

import business.constants.Role;
import business.entity.Users;
import business.feature.IUserFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserFeatureImpl implements IUserFeature {
    public static List<Users> usersList = new ArrayList<>();

    public  UserFeatureImpl (){
        usersList = IOFile.readFromFile(IOFile.PATH_USER);
    }


    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);
            // khởi tạo đối tượng user
            Users user = new Users();
            user.inputRegister(scanner);
            user.setRoleName(Role.ROLE_USER);
            // add đối tượng vừa đăng đý vào list
             usersList.add(user);
        // cập nhật lại list users trong file
        IOFile.writeToFile(IOFile.PATH_USER, usersList);
    }


    @Override
    public Users login(String email, String password) {
        Optional<Users> optionalUsers = usersList.stream()
                .filter(item ->
                        item.getEmail().equals(email) &&
                                item.getPassword().equals(password)
                ).findFirst();
        if (optionalUsers.isPresent()) {
            return optionalUsers.get();
        }
        return null;
    }
}
