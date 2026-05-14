package com.ffucks;

import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.*;

import java.util.List;

@GraphQLApi
public class UserGraphQlResource {

    @Inject
    UserService userService;

    @Query("users")
    public List<User> users() {
        return userService.findAll();
    }

    @Query("userById")
    public User userById(@Name("id") Long id) {
        return userService.findById(id);
    }

    @Mutation
    public User createUser(
            @Name("name") String name,
            @Name("email") String email
    ) {

        User user = new User(name, email);

        return userService.create(user);
    }

    @Mutation
    public boolean deleteUser(@Name("id") Long id) {
        return userService.delete(id);
    }
}