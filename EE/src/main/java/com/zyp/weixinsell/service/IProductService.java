package com.zyp.weixinsell.service;


import com.zyp.weixinsell.dto.CartDTO;
import com.zyp.weixinsell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IProductService {
    Optional<ProductInfo> findOne(String productId);

    /**
     * 查询所有上架商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();
   /*分页*/
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);
   /*加库存*/
    void increaseStock(List<CartDTO> cartDTOList);
   /*减库存*/
    void decreaseStock(List<CartDTO> cartDTOList);

    ProductInfo onSale(String productId);

    ProductInfo offSale(String productId);
}
