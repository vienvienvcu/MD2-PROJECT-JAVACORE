package business.entity;

import business.feature.Impl.AddressFeatureImpl;
import business.feature.Impl.UserFeatureImpl;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Address implements Serializable {
    private int addressId;
    private Users user;
    private String fullAddress;
    private String phone;
    private String receiveName;


//    ****************CONTRACTOR***********************:
public Address(){

    }

    public Address(int addressId, String fullAddress, String phone, String receiveName, Users user) {
        this.addressId = addressId;
        this.fullAddress = fullAddress;
        this.phone = phone;
        this.receiveName = receiveName;
        this.user = user;
    }

//  ****************METHOD GET/SET***********************:


    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

//    ****************INPUT ADDRESS DATA***********************:

    public void inputAddressData(Scanner scanner){
         this.addressId =inputAddressData();

         this.fullAddress = inputFullAddress(scanner);
         this.phone = inputPhone(scanner);
         this.receiveName = inputReceiveName(scanner);

    }

//   ========================VALIDATION=========================

    public int inputAddressData(){
        int idAddressMax =0;
        for (Address address: AddressFeatureImpl.addressList ){
            if(address.getAddressId() > idAddressMax){
                idAddressMax = address.getAddressId();
            }
        }
        return idAddressMax + 1;
    }


    public String inputFullAddress(Scanner scanner) {
        System.out.println("Enter full address: ");
        do {
            String fullAddress = scanner.nextLine();
            if (fullAddress.isEmpty()) {
                System.err.println("Invalid full address. Please enter a valid full address.");
            }else {
                return fullAddress;
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

    public String inputReceiveName (Scanner scanner) {
        System.out.println("Enter receive name: ");
        do {
            String receiveName = scanner.nextLine();
            if (receiveName.isEmpty()){
                System.err.println("Receive name cannot be empty,please try again");
            }else {
                return receiveName;
            }
        }while (true);
    }
    public static int inputNumber(Scanner scanner) {
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please a input number choice");
            }
        } while (true);

    }

//    ****************SHOW ADDRESS DATA***********************:
    public void displayAddressData(){
        String format = "| %-20s | %-20s | %-30s | %-20s | %-20s |\n";
        String separator = "+----------------------+----------------------+--------------------------------+----------------------+----------------------+\n";

        // Print header
        System.out.print(separator);
        System.out.printf(format, "Address ID", "User Name", "Full Address", "Phone", "Receive Name");
        System.out.print(separator);

        // Print address data
        System.out.printf(format, addressId, this.user.getUserName(), fullAddress, phone, receiveName);
        System.out.print(separator);
        System.out.println();

    }
}
