package com.example.demo.factory;

import com.example.demo.domain.User;

public class ServiceLoaderUserFactory implements UserFactory {

    @Override
    public User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("ServiceLoader装配");
        return user;
    }
}
