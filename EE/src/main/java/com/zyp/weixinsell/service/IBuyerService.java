package com.zyp.weixinsell.service;


import com.zyp.weixinsell.dto.OrderDTO;

/**
 * 买家service
 */
public interface IBuyerService {
    OrderDTO findOneOrder(String openid, String orderId);

    OrderDTO cancleOrder(String openid, String orderId);
}
