package business.entity;

import business.constants.Role;
import business.feature.Impl.UserFeatureImpl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

//***********************THUOC TINH****************************************


public class Users implements Serializable {
    private int usersId;
    private String userName;
    private String fullName;
    private boolean gender;
    private String address;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
    private Date creationDate;
    private Date updatedDate;
    private boolean statusUser;
    private boolean isDeleted;
    private Role roleName;

//***********************CONTRACTOR****************************************

    public Users() {
    }

    public Users(String address, String confirmPassword, Date creationDate,
                 String email, String fullName, boolean gender, boolean isDeleted,
                 String password, String phone, Role roleName, boolean statusUser,
                 Date updatedDate, String userName, int usersId) {
        this.address = address;
        this.confirmPassword = confirmPassword;
        this.creationDate = creationDate;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.isDeleted = isDeleted;
        this.password = password;
        this.phone = phone;
        this.roleName = roleName;
        this.statusUser = statusUser;
        this.updatedDate = updatedDate;
        this.userName = userName;
        this.usersId = usersId;
    }
//********************GET/SET********************************

    public boolean isDelete() {
        return isDeleted;
    }

    public void setDelete(boolean delete) {
        isDeleted = delete;
    }

    public Date getUpdated() {
        return updatedDate;
    }

    public void setUpdated(Date updated) {
        this.updatedDate = updated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isStatusUser() {
        return statusUser;
    }

    public void setStatusUser(boolean statusUser) {
        this.statusUser = statusUser;
    }

//    ========================INPUT USER=====================================

    public void inputUser(Scanner scanner){
        this.usersId = inputUsersId();
        this.userName = inputNameUser(scanner);
        this.fullName = inputFullName(scanner);
        this.gender = inputGender(scanner);
        this.address = inputAddress(scanner);
        this.email = inputMail(scanner);
        this.phone = inputPhone(scanner);
        this.password = inputPassword(scanner);
        this.confirmPassword = inputConfirmPassword(scanner);
        this.creationDate = new Date();
        this.updatedDate = this.creationDate;
        this.statusUser = statusUser(scanner);
        this.isDeleted = isDeleted(scanner);
    }
//    ========================INPUT REGISTER=====================================

    public void inputRegister(Scanner scanner) {
        this.userName = inputNameUser(scanner);
        this.phone = inputPhone(scanner);
        this.email = inputMail(scanner);
        this.password = inputPassword(scanner);
        this.confirmPassword = inputConfirmPassword(scanner);
    }

//    ========================VALIDATION=====================================

    public int inputUsersId() {
      int idMax = 0;
      for (Users users : UserFeatureImpl.usersList){
          if(users.getUsersId() > idMax){
              idMax = users.getUsersId();
          }
      }
      return idMax + 1;
    }

    public String inputNameUser(Scanner scanner) {
        System.out.println("Enter username: ");
        do {
            String username = scanner.nextLine();
            if (username.isEmpty()) {
                System.err.println("Username cannot be empty,please try again");
            }else {
                if (username.length() > 6 && username.length()<=100) {
                    return username;
                }else {
                    System.err.println("Username must be less >6 characters long and less than 100 characters");
                }
            }
        }while (true);

    }

    public String inputFullName(Scanner scanner){
        System.out.println("Enter full name: ");
        do {
            String fullName = scanner.nextLine();
            if (fullName.isEmpty()) {
                System.err.println("Full name cannot be empty,please try again");
            }else {
                return fullName;
            }
        }while (true);
    }

    public Boolean inputGender(Scanner scanner) {
        System.out.println("Enter Gender: ");
        do {
            String gender = scanner.nextLine();
            if (gender.isEmpty()){
                System.err.println("Gender cannot be empty,please try again");
            }else {
                if (gender.equals("male")||gender.equals("female")) {
                    return Boolean.parseBoolean(gender);
                }
            }
        }while (true);
    }

    public String inputMail(Scanner scanner) {
        System.out.println("Enter email: ");
        do {
            String email = scanner.nextLine();
            String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
            if (Pattern.matches(emailRegex,email)){
                boolean isExists = false;
                for (Users users : UserFeatureImpl.usersList) {
                    if (users.getEmail().equals(email)){
                        isExists = true;
                        break;
                    }
                }
                if (isExists){
                    System.err.println("Email existed, please try again");
                }else {
                    return email;
                }
            }else {
                System.err.println("Invalid email format,please try again 'xxxx@gmail.com'");
            }

        }while (true);
    }

    public String inputPhone(Scanner scanner) {
        System.out.println("Enter phone number: ");
        do {
            String phone = scanner.nextLine();
            String phoneRegex = "^\\+[1-9]\\d{1,14}$";
            if (phone.isEmpty()){
                System.err.println("Phone number cannot be empty,please try again");
            }else {
               if (Pattern.matches(phoneRegex,phone)){
                   boolean isExists = false;
                   for (Users users : UserFeatureImpl.usersList) {
                       if (users.getPhone().equals(phone)){
                           isExists = true;
                           break;
                       }
                   }
                   if (isExists){
                       System.err.println("Phone number existed, please try again");
                   }else {
                       return phone;
                   }
               }else {
                   System.err.println("Invalid phone format,please try again ''");
               }
            }

        }while (true);

    }

    public String inputAddress(Scanner scanner) {
        System.out.println("Enter address: ");
        do {
            String address = scanner.nextLine();
            if (address.isEmpty()){
                System.err.println("Address cannot be empty,please try again");
            }else {
                return address;
            }
        }while (true);
    }

    public String inputPassword(Scanner scanner) {
        System.out.println("Enter password: ");
        do {
            String password = scanner.nextLine();
            if (password.isEmpty()){
                System.err.println("Password cannot be empty,please try again");
            }else {
                if (password.length() < 6){
                    System.err.println("Password must be at least 6 characters");
                }else {
                    return password;
                }
            }

        }while (true);
    }

    public String inputConfirmPassword(Scanner scanner) {
        System.out.println("Enter confirm password: ");
        do {
            String confirmPassword = scanner.nextLine();
            if (confirmPassword.isEmpty()){
                System.err.println("Confirm password cannot be empty,please try again");
            }else {
                if (confirmPassword.equals(this.password)){

                    return confirmPassword;

                }else {
                    System.err.println("Confirm password does not match,please try again");
                }
            }
        }while (true);
    }


    public boolean statusUser(Scanner scanner) {
        System.out.println("Enter status user: ");
        do {
            String status = scanner.nextLine();
            if (status.isEmpty()){
                System.err.println("Status cannot be empty,please try again");
            }else {
                if (status.equals("true")||status.equals("false")){
                    return Boolean.parseBoolean(status);
                }else {
                    System.err.println("Status user only 2 value 'true' or 'false',please try again");
                }
            }
        }while (true);
    }

    public boolean isDeleted(Scanner scanner) {
        System.out.println("Enter deleted status: ");
        do {
            String statusDelete = scanner.nextLine();
            if (statusDelete.equals("1")||statusDelete.equals("0")){
                return Boolean.parseBoolean(statusDelete);
            }else {
                System.err.println("Status user only 2 value '1' or '0',please try again");
            }
        }while (true);
    }

//     ========================SHOW USER=====================================

    public void displayUsers() {
        System.out.println("==================================================================================================================================");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s \n",
        "User ID","User Name","Full name","Gender","Address","Email","Phone","password","Confirm Password",
        "Creation Date","Update Date","Status","Deleted","Role name");
        System.out.printf("%-10d %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s \n",
                this.usersId,this.userName,this.fullName,this.gender?"male":"female",
                this.address,this.email,this.phone,this.password,this.confirmPassword,
                dateFormat.format(this.creationDate),dateFormat.format(this.updatedDate),
                this.statusUser?"Active":"Inactive",this.isDeleted?"Active":"Deleted",this.roleName);
    }

//     ========================SHOW LOG IN=====================================

    public void displayLogIn() {
        System.out.println("=================================================================");
        System.out.printf("%-20s %-20s %-20s\n",
              "EMail","PassWord","Confirm Password");
        System.out.printf("%-20s %-20s\n",
              this.email,this.password);

    }

//     ========================SHOW REGISTER=====================================

    public void displayRegister() {
        System.out.println("==============================================================================================");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                "User name","Phone","EMail","PassWord","Confirm Password");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                this.userName,this.phone,this.email,this.password,this.confirmPassword);

    }

}
