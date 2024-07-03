package business.feature;

import business.entity.Users;

public interface IUserFeature {
    void register(); ;

    Users login(String email, String password);
}
