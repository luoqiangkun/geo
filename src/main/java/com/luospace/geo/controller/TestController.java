package com.luospace.geo.controller;

import com.luospace.geo.common.Result;
import com.luospace.geo.dao.ProductDao;
import com.luospace.geo.entity.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@Api( "测试")
public class TestController {
    @Resource
    ProductDao productDao;

    @ApiOperation(value = "商品列表", notes = "获取商品列表数据")
    @RequestMapping("/list")
    public Result list(){
        Iterable<Product> products = productDao.findAll();
        return Result.success(products);
    }

    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result list(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        Sort sort=Sort.by(Sort.Direction.DESC,"id");
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize,sort);
        Page<Product> productPage = productDao.findAll(pageRequest);
        return Result.success(productPage);
    }

    @RequestMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id){
        Optional<Product> product = productDao.findById(id);
        return Result.success(product);
    }

    @RequestMapping("/save")
    public void save(){
        for( int i = 1; i < 20; i++ ){
            Product product = new Product();
            product.setId(Long.valueOf(i));
            product.setName("小米手机" +i);
            productDao.save(product);
        }
    }
}
