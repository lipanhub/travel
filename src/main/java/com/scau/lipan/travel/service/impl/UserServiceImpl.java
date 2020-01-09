package com.scau.lipan.travel.service.impl;

import com.scau.lipan.travel.dao.UserDao;
import com.scau.lipan.travel.dao.impl.UserDaoImpl;
import com.scau.lipan.travel.domain.User;
import com.scau.lipan.travel.service.UserService;
import com.scau.lipan.travel.util.MailUtils;
import com.scau.lipan.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {


    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {


        if (null != userDao.findByUsername(user.getUsername())){
            return false;
        }

        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");

        String content="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活【旅游网】</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        userDao.save(user);
        return true;
    }

    @Override
    public boolean active(String code) {


        User user = userDao.findByCode(code);

        if(null != user){
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(User tmp_user) {
        return userDao.findByUsernameAndPassword(tmp_user.getUsername(),tmp_user.getPassword());
    }
}
