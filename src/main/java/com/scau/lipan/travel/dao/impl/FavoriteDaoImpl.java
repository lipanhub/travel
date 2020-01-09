package com.scau.lipan.travel.dao.impl;

import com.scau.lipan.travel.dao.FavoriteDao;
import com.scau.lipan.travel.domain.Favorite;
import com.scau.lipan.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite =null;
        try{

            String sql = "select * from tab_favorite where rid=? and uid=?";
            favorite = template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);
        }catch (Exception e){
        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid=?";
        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public boolean add(int rid, int uid) {

        String sql = "insert into tab_favorite values(?,?,?)";

        return template.update(sql,rid,new Date(),uid)>0;

    }
}
