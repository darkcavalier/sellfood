package com.zyp.weixinsell.service;

import com.zyp.weixinsell.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface IProductCategoryService {
    Optional<ProductCategory> findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
