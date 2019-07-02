package com.zyp.weixinsell.service.impl;

import com.zyp.weixinsell.dto.OrderDTO;
import com.zyp.weixinsell.entity.OrderDetail;
import com.zyp.weixinsell.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private IOrderService service;
    private static final String buyerOpenid = "110110";
    private static final String orderId = "1555411089134843686";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("清湖");
        orderDTO.setBuyerOpenid(buyerOpenid);
        orderDTO.setBuyerName("彩虹");
        orderDTO.setBuyerPhone("13423562345");
        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(1);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123457");
        orderDetail2.setProductQuantity(1);
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = service.create(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = service.findOne(orderId);
        log.info("查询订单详情为: " + orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findOrderList() throws Exception {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = service.findOrderList(buyerOpenid, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancleOrder() throws Exception {//取消订单
        OrderDTO orderDTO = service.findOne(orderId);
        OrderDTO result = service.cancleOrder(orderDTO);
        Assert.assertEquals(new Integer(2), result.getOrderStatus());
    }

    @Test
    public void finishOrder() throws Exception {
        OrderDTO orderDTO = service.findOne(orderId);
        OrderDTO result = service.finishOrder(orderDTO);
        Assert.assertEquals(new Integer(1), result.getOrderStatus());
    }

    @Test
    public void payOrder() throws Exception {
        OrderDTO orderDTO = service.findOne(orderId);
        OrderDTO result = service.payOrder(orderDTO);
        Assert.assertEquals(new Integer(1), result.getOrderStatus());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> page = service.findList(request);
        Assert.assertTrue("查询所有订单列表", page.getTotalElements() > 0);
    }

}