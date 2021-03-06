/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db.dao;

import app.db.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author NavNag
 */
@Repository
public interface OrdersDao extends CrudRepository<Order, Long> {

    public Order findById(long id);
}
