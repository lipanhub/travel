package cn.itcast.travel.service.impl;

import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

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
