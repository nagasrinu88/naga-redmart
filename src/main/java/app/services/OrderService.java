/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.AppSettings;
import app.db.Order;
import app.db.OrderItems;
import app.db.Product;
import app.db.dao.OrderItemsDao;
import app.db.dao.OrdersDao;
import app.db.dao.ProductsDao;
import app.forms.OrderForm;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author NavNag
 */
@Component
public class OrderService {

    @Autowired
    private OrderItemsDao orderItemsDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private ProductsDao productsDao;
    @Autowired
    public AppSettings settings;

    /**
     *
     * @param orderForm
     * @return
     */
    public Order placeOrder(OrderForm orderForm) {
        if (orderForm.getItems().size() > settings.getItemsPerVehicle()) {
            throw new IndexOutOfBoundsException("Order Items Cannot be more that" + settings.getItemsPerVehicle());
        }
        Order order = new Order();
        order.setComments(orderForm.getComments());
        order.setName(orderForm.getName());
        List<Product> products = productsDao.findAllBySku(orderForm.getSkuList());
        order.setTotalItems(products.size());
        ordersDao.save(order); // Saving the Order First
        List<OrderItems> items = new ArrayList<OrderItems>();
        for (Product product : products) {
            OrderItems item = new OrderItems();
            item.setDimension(product.getDimension());
            item.setOrderId(order.getId());
            item.setProductId(product.getId());
            items.add(item);
        }
        // Now saving the Items
        orderItemsDao.save(items);
        return order;
    }
}
