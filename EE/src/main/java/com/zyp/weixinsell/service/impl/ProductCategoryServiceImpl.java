package com.zyp.weixinsell.service.impl;

import com.zyp.weixinsell.entity.ProductCategory;
import com.zyp.weixinsell.repository.ProductCategoryRepository;
import com.zyp.weixinsell.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 商品类目表Service实现类
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService{
    @Autowired
    private ProductCategoryRepository reposity;
    @Override
    public Optional<ProductCategory> findOne(Integer categoryId) {
        return reposity.findById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return reposity.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return reposity.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return reposity.save(productCategory);
    }
}
