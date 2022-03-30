package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1.mapper.ProductMapper;
import com.example.demo1.model.ProductDO;
import com.example.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductMapper productMapper;

    @Override
    public int save(ProductDO productDO) {
        return productMapper.insert(productDO);
    }

    @Override
    public int delById(int id) {
        return productMapper.deleteById(id);
    }

    @Override
    public int updateById(ProductDO productDO) {
        return productMapper.updateById(productDO);
    }

    @Override
    @Cacheable(value = {"product"} , key = "#root.args[0]")
    public ProductDO findById(int id) {
        return productMapper.selectById(id);
    }

    @Override
    public Map<String, Object> page(int page, int size) {

        Page pageInfo = new Page(page,size);

        Page<ProductDO>  productDOPage= productMapper.selectPage(pageInfo, null);

        Map<String,Object> pageMap = new HashMap<>(3);

        pageMap.put("total_record",productDOPage.getTotal());
        pageMap.put("total_page",productDOPage.getPages());
        pageMap.put("current_data",productDOPage.getRecords());

        return pageMap;
    }
}
