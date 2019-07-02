package com.zyp.weixinsell.repository;

import com.zyp.weixinsell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情repository
 */
public interface OrderDetailReposity extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
