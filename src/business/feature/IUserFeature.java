package business.feature;

import business.entity.Users;

public interface IUserFeature extends IGenericFeature<Users, Integer> {
    void register(); ;
    void addDetailUser(Users user);

    Users login(String email, String password);
}
