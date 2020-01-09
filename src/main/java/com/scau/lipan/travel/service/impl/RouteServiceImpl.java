package com.scau.lipan.travel.service.impl;

import com.scau.lipan.travel.dao.FavoriteDao;
import com.scau.lipan.travel.dao.RouteDao;
import com.scau.lipan.travel.dao.RouteImgDao;
import com.scau.lipan.travel.dao.SellerDao;
import com.scau.lipan.travel.dao.impl.FavoriteDaoImpl;
import com.scau.lipan.travel.dao.impl.RouteDaoImpl;
import com.scau.lipan.travel.dao.impl.RouteImgDaoImpl;
import com.scau.lipan.travel.dao.impl.SellerDaoImpl;
import com.scau.lipan.travel.domain.PageBean;
import com.scau.lipan.travel.domain.Route;
import com.scau.lipan.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    RouteDao routeDao = new RouteDaoImpl();
    RouteImgDao routeImgDao = new RouteImgDaoImpl();
    SellerDao sellerDao = new SellerDaoImpl();
    FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize ,String rname) {
        PageBean<Route> pageBean = new PageBean<Route>();

        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);

        int totalCount = routeDao.findTotalCount(cid,rname);
        pageBean.setTotalCount(totalCount);

        List<Route> list = routeDao.findByPage(cid,(currentPage-1)*pageSize,pageSize,rname);
        pageBean.setList(list);

        int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public Route findOne(int rid) {

        Route route = routeDao.findOne(rid);
        route.setRouteImgList(routeImgDao.findByRid(rid));
        route.setSeller(sellerDao.findBySid(route.getSid()));
        route.setCount(favoriteDao.findCountByRid(rid));
        return route;
    }
}
