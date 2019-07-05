package com.store.store.repositories;

import com.store.store.model.ProductsBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsBean, String> { }
