package com.scau.lipan.travel.dao.impl;

import com.scau.lipan.travel.dao.CategoryDao;
import com.scau.lipan.travel.domain.Category;
import com.scau.lipan.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
