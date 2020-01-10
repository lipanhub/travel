package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());



    @Override
    public User findByUsername(String username) {
        User user = null;

        //TODO 这里必须加try catch 否则运行时出错   但是编译不报错  神奇 网上找到了个帖子，有空看看
        //TODO 我明白了这里queryForObject()的返回值期望是1，结果是零，因此抛出异常  expected 1, actual 0
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public void save(User user) {
        //1.定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql

        template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status='Y' where uid=?";
        template.update(sql,user.getUid());
    }

    @Override
    public User findByCode(String code) {
        User user = null;

        //TODO 这里必须加try catch 否则运行时出错   但是编译不报错  神奇 网上找到了个帖子，有空看看
        //TODO 我明白了这里queryForObject()的返回值期望是1，结果是零，因此抛出异常  expected 1, actual 0
        try {
            //1.定义sql
            String sql = "select * from tab_user where code = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public User findByUsernameAndPassword(String username,String password) {
        User user = null;

        //TODO 这里必须加try catch 否则运行时出错   但是编译不报错  神奇 网上找到了个帖子，有空看看
        //TODO 我明白了这里queryForObject()的返回值期望是1，结果是零，因此抛出异常  expected 1, actual 0
        try {
            //1.定义sql
            String sql = "select * from tab_user where username=? and password=?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (Exception e) {

        }
        return user;
    }
}
