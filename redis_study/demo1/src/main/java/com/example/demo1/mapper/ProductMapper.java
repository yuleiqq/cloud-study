package com.example.demo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo1.model.ProductDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProductMapper extends BaseMapper<ProductDO> {



}
