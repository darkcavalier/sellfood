package com.zyp.weixinsell.service.impl;

import com.zyp.weixinsell.entity.ProductInfo;
import com.zyp.weixinsell.service.IProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceTest {
    @Autowired
    private IProductService productService;

    @Test
    public void findOne() {
        Optional<ProductInfo> result = productService.findOne("123456");
        Assert.assertEquals("123456", result.get().getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> result = productService.findUpAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage =
                productService.findAll(request);
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.5));
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("正宗西安豆腐脑");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }
}

