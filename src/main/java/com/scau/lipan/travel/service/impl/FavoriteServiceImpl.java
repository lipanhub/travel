package com.scau.lipan.travel.service.impl;

import com.scau.lipan.travel.dao.FavoriteDao;
import com.scau.lipan.travel.dao.impl.FavoriteDaoImpl;
import com.scau.lipan.travel.domain.Favorite;
import com.scau.lipan.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    private  FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(int rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(rid,uid);
        return favorite!=null;
    }

    @Override
    public boolean add(int rid, int uid) {
        return favoriteDao.add(rid,uid);
    }
}
