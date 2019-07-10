package com.store.store.repositories;

import com.store.store.model.ClientsBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends CrudRepository<ClientsBean,String> {


    ClientsBean findByUsername(String s);
}
