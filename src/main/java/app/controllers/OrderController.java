/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.db.Order;
import app.db.Slot;
import app.db.dao.OrdersDao;
import app.db.dao.SlotDao;
import app.forms.OrderForm;
import app.scripts.Script;
import app.services.OrderService;
import app.services.SlotService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author NavNag
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    static final Logger log = Logger.getLogger(OrderController.class.getName());

    @Autowired
    private OrderService orderService;
    @Autowired
    private SlotService slotService;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private SlotDao slotDao;

    @RequestMapping(value = "/")
    public Order order() {
        Order order = orderService.placeOrder(Script.randomOrder());
        return order;
    }

    @RequestMapping(value = "/{id}/book-slot")
    public Slot bookslot(@PathVariable("id") long orderId) {
        log.log(Level.INFO, "Given Order Id {0}", orderId);
        Order order = ordersDao.findById(orderId);
        log.log(Level.INFO, "Order Object {0}", order);
        if (order != null) {
            if (order.isAllotted()) {
                return slotDao.findByOrderId(orderId);
            } else {
                return slotService.bookSlot(order);
            }

        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}/status")
    public Object status(@PathVariable("id") long orderId) {
        log.log(Level.INFO, "Given Order Id {0}", orderId);
        Order order = ordersDao.findById(orderId);
        log.log(Level.INFO, "Order Object {0}", order);
        if (order == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        if (!order.isAllotted()) {
            return "Order is not allotted yet";
        }
        Slot slot = slotDao.findByOrderId(orderId);
        if (slot == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return slot;
    }
}
