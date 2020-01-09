package com.scau.lipan.travel.service;

import com.scau.lipan.travel.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    boolean active(String code);

    User login(User tmp_user);
}
