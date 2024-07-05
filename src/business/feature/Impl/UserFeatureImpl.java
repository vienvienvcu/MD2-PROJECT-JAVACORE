package business.feature.Impl;

import business.constants.RoleName;
import business.entity.Users;
import business.feature.IUserFeature;
import business.utils.IOFile;

import java.util.*;

public class UserFeatureImpl implements IUserFeature {
    public static List<Users> usersList;
    public static Users userLogin ;

    static {
        usersList = IOFile.readFromFile(IOFile.PATH_USER);
        if (usersList == null) {
            usersList = new ArrayList<>();
        }
    }

    public UserFeatureImpl() {
        if (usersList == null) {
            usersList = IOFile.readFromFile(IOFile.PATH_USER);
            if (usersList == null) {
                usersList = new ArrayList<>();
            }
        }
    }


    public List<Users> getUsersList() {
        return usersList;
    }
    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);
            // khởi tạo đối tượng user
            Users user = new Users();
            user.inputRegister(scanner);
            user.setRoleName(RoleName.ROLE_USER);
            // add đối tượng vừa đăng đý vào list
             usersList.add(user);
        System.out.println("User registered: " + user.getUserName());

        // cập nhật lại list users trong file
        IOFile.writeToFile(IOFile.PATH_USER, usersList);
    }

    @Override
    public void addDetailUser(Users user) {
        Boolean indexCheck = findByName(user.getUserName());
        if(indexCheck == null){
            usersList.add(user);
        }else {
            for(int i = 0; i<usersList.size(); i++){
                if(usersList.get(i).getUserName().equals(user.getUserName())){
                    usersList.set(i,user);
                    break;
                }
            }
        }
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
            System.out.println("User logged in: " + optionalUsers.get().getUserName());
        }else {
            System.out.println("Login failed for email: " + email);
        }
       return optionalUsers.orElse(null);
    }

    @Override
    public List<Users> getAll() {
        return usersList;
    }

    @Override
    public void save(Users users) {
        int indexCheck = findById(users.getUsersId());
        if(indexCheck < 0){
            usersList.add(users);
        }else {
            usersList.set(indexCheck, users);
        }
        IOFile.writeToFile(IOFile.PATH_USER, usersList);
    }

    @Override
    public Integer findById(Integer id) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUsersId() == id){
                return i;
            }
        }
        return -1;
    }

    public Boolean findByName(String name) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        int indexDelete = findById(id);
        if (indexDelete >= 0) {
            usersList.remove(indexDelete);
            IOFile.writeToFile(IOFile.PATH_USER, usersList);
        }else {
          System.err.println("User not found");
        }
    }

    public static void sortUserById() {
        Collections.sort(UserFeatureImpl.usersList);
        for (Users user : UserFeatureImpl.usersList) {
            user.displayUserData();
        }
        System.out.println("You have successfully sorted users by id ");
        IOFile.writeToFile(IOFile.PATH_USER, usersList);
    }
}
