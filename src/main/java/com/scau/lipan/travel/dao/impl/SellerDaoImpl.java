package com.scau.lipan.travel.dao.impl;

import com.scau.lipan.travel.dao.SellerDao;
import com.scau.lipan.travel.domain.Seller;
import com.scau.lipan.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findBySid(int sid) {

        String sql = "select * from tab_seller where sid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }
}
