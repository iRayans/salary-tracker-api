package com.rayan.salarytracker.reposetory;

import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.security.PasswordUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>  {


    public User findUserByEmail(String email) {
        return find("email = ?1", email).firstResult();
    }

    public Boolean isEmailExists(String email) {
        return count("email", email) > 0;
    }

    public Boolean isUserValid(String email, String plainPassword) {
        User user = findUserByEmail(email);
        if (user == null) {
            return false;
        }
        return PasswordUtil.checkPassword(plainPassword, user.getPassword());
    }

    public Boolean isUsernameExists(String username) {
        return count("username = ?1", username) > 0;
    }
}
