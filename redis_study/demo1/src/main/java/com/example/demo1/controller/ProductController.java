package com.example.demo1.controller;


import com.example.demo1.model.ProductDO;
import com.example.demo1.service.ProductService;
import com.example.demo1.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
 
    @Autowired
    ProductService productService;


    @PostMapping("add")
    public JsonData add(@RequestBody ProductDO productDO){

        productDO.setCreateTime(new Date());
        int rows = productService.save(productDO);

        return JsonData.buildSuccess(rows);

    }

    @PostMapping("update")
    public JsonData update(@RequestBody ProductDO productDO){

        productDO.setCreateTime(new Date());
        int rows = productService.updateById(productDO);
        return JsonData.buildSuccess(rows);

    }

    @PostMapping("del")
    public JsonData delete( int id){

        int rows = productService.delById(id);
        return JsonData.buildSuccess(rows);

    }


    @PostMapping("find")
    public JsonData findById( int id){

        ProductDO productDO = productService.findById(id);

        return JsonData.buildSuccess(productDO);
    }



    @GetMapping("page")
    public JsonData page(int page, int size){

        Map<String, Object> map = productService.page(page, size);

        return JsonData.buildSuccess(map);

    }



}
