package com.scau.lipan.travel.dao;

import com.scau.lipan.travel.domain.Favorite;

public interface FavoriteDao {

    public Favorite findByRidAndUid(int rid, int uid);

    public int findCountByRid(int rid);


    public boolean add(int rid ,int uid);
}
