package com.zyp.weixinsell.service.impl;


import com.zyp.weixinsell.entity.SellerInfo;
import com.zyp.weixinsell.repository.SellerInfoReposity;
import com.zyp.weixinsell.service.ISellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SellerInfoServiceImpl implements ISellerInfoService {
    @Autowired
    private SellerInfoReposity sellerInfoReposity;

    @Override
    public SellerInfo findByUserName(String username) {
        SellerInfo sellerInfo = sellerInfoReposity.findByUsername(username);
        if (sellerInfo != null) {
            log.info("找到指定用户");
            return sellerInfo;
        } else {
            log.error("用户不存在..");
            return null;
        }
    }
}
