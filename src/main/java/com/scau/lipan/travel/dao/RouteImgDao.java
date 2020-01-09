package com.scau.lipan.travel.dao;

import com.scau.lipan.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    public List<RouteImg> findByRid(int rid);
}
