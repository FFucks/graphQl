package com.ffucks;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.id.equals(id))
                .findFirst();
    }

    public User create(User user) {
        users.add(user);
        return user;
    }

    public boolean delete(Long id) {
        return users.removeIf(user -> user.id.equals(id));
    }
}
