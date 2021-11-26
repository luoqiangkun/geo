package com.luospace.geo.dao;


import com.luospace.geo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}
