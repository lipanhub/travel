package com.scau.lipan.travel.dao;

import com.scau.lipan.travel.domain.User;

public interface UserDao {

    User findByUsername(String username);
    void save(User user);

    void updateStatus(User user);

    User findByCode(String code);

    User findByUsernameAndPassword(String username,String password);
}
