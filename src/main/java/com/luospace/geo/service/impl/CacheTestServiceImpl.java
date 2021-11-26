package com.luospace.geo.service.impl;

import com.luospace.geo.entity.Product;
import com.luospace.geo.service.CacheTestService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "user")
public class CacheTestServiceImpl implements CacheTestService {
    @Override
    @Cacheable(value = "test")
    public List<Product> get() {
        List<Product> products = new ArrayList<Product>();
        for( int i = 0; i < 20;i++ ){
            Product product = new Product();
            product.setId(Long.valueOf(i));
            product.setName("测试商品" + i);
            products.add(product);
        }
        return products;
    }
}
