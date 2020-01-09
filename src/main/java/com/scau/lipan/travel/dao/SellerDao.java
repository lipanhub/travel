package com.scau.lipan.travel.dao;

import com.scau.lipan.travel.domain.Seller;

public interface SellerDao {

    public Seller findBySid(int sid);
}
