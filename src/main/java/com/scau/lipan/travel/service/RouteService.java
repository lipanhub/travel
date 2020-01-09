package com.scau.lipan.travel.service;

import com.scau.lipan.travel.domain.PageBean;
import com.scau.lipan.travel.domain.Route;

public interface RouteService {

    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize ,String rname);

    public Route findOne(int rid);
}
