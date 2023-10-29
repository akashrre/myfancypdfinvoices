package com.akashreddy.myfancypdfinvoices.service;

import com.akashreddy.myfancypdfinvoices.model.User;

import java.util.UUID;

public class UserService {

    public User findById(String id) {
        String randomName = UUID.randomUUID().toString();
        return new User(id, randomName);
    }
}
