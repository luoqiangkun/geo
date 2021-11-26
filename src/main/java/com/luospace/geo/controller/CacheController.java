package com.luospace.geo.controller;

import com.luospace.geo.common.Result;
import com.luospace.geo.entity.Product;
import com.luospace.geo.service.CacheTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/cache")
@RestController
public class CacheController {
    @Resource
    CacheTestService cacheTestService;

    @RequestMapping("/test")
    public Result test(){
        List<Product> products = cacheTestService.get();
        return Result.success(products);
    }


    @RequestMapping("/get")
    @Cacheable("test111")
    public Result get(){
        return Result.success(11111);
    }
}
