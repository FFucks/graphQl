package com.ffucks;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

    public List<User> findAll() {
        return User.listAll();
    }

    public User findById(Long id) {
        return User.findById(id);
    }

    @Transactional
    public User create(User user) {
        user.persist();
        return user;
    }

    @Transactional
    public boolean delete(Long id) {
        User user = User.findById(id);

        if (user == null) {
            return false;
        }

        user.delete();
        return true;
    }
}
