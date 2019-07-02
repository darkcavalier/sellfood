package com.zyp.weixinsell.service;


import com.zyp.weixinsell.entity.SellerInfo;

/**
 * 卖家登录Service
 */
public interface ISellerInfoService {
    SellerInfo findByUserName(String username);
}
