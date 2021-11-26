package com.luospace.geo;

import com.luospace.geo.common.Constant;
import com.luospace.geo.config.ElasticSearchConfig;
import com.luospace.geo.dao.ProductDao;
import com.luospace.geo.entity.Product;
import com.luospace.geo.util.RedisUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GeoApplicationTests {
    @Resource
    ProductDao productDao;
    @Test
    public void save() {
        Product product = new Product();
        product.setId(3L);
        product.setName("hello world");
        productDao.save(product);
    }

    /**
     * 批量新增
     */
    @Test
    public void saveAll() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Product product = new Product();
            product.setId(Long.valueOf(i));
            product.setName("[" + i + "]小米手机");
        }
        productDao.saveAll(productList);
    }

    /**
     * 根据id查询
     */
    @Test
    public void findById() {
        Product product = productDao.findById(2L).get();
        System.out.println(product);
    }

    /**
     * 查询所有
     */
    @Test
    public void findAll() {
        productDao.findAll().forEach(product -> {
            System.out.println(product);
        });
    }

    /**
     * 分页查询
     */
    @Test
    public void findByPage() {
        //设置排序规则
        Sort sort=Sort.by(Sort.Direction.DESC,"id");
        int currentPage=0;//当前页
        int pageSize=5;//每页显示的数据
        PageRequest pageRequest=PageRequest.of(currentPage,pageSize,sort);
        Page<Product> productPage = productDao.findAll(pageRequest);
        for (Product product : productPage) {
            System.out.println(product);
        }
    }
}
