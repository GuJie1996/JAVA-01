package com.example.demo.factory;

import com.example.demo.domain.User;

public interface UserFactory {

    default User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("实例方法装配");
        return user;
    }

}
