package com.scau.lipan.travel.dao.impl;

import com.scau.lipan.travel.dao.RouteImgDao;
import com.scau.lipan.travel.domain.RouteImg;
import com.scau.lipan.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findByRid(int rid) {

        String sql = "select * from tab_route_img where rid=?";
        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
}
