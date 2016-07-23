/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db.dao;

import app.db.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author NavNag
 */
@Repository
public interface ProductsDao extends CrudRepository<Product, Long> {

    @Query("from Product p where p.sku in ?1")
    List<Product> findAllBySku(List<String> skus);
}
