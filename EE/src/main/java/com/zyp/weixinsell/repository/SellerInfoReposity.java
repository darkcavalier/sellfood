package com.zyp.weixinsell.repository;

import com.zyp.weixinsell.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家信息
 */
public interface SellerInfoReposity extends JpaRepository<SellerInfo, String> {
    SellerInfo findByUsername(String username);
}
