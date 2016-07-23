/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.scripts;

import app.AppSettings;
import app.db.Dimension;
import app.db.Product;
import app.db.TransportVehicle;
import app.db.dao.ProductsDao;
import app.db.dao.TransportVehicleDao;
import app.forms.OrderForm;
import app.services.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author NavNag
 */
@Component
public class Script {

    static final Logger log = Logger.getLogger(Script.class.getName());

    private static final int totalProducts = 10;
    private static final int totalVehicles = 4;
    private static final int totalOrders = 50;
    private static final Random rnd = new Random();
    @Autowired
    private ProductsDao productsDao;
    @Autowired
    private TransportVehicleDao transportVehicalDao;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AppSettings appSettings;

    public void createProducts() {
        log.log(Level.INFO, "Trying to create {0} products", totalProducts);
        for (int i = 0; i < totalProducts; i++) {
            Product product = new Product();
            product.setName("product - " + (i + 1));
            product.setSku("sku_" + (i + 1));
            product.setPrice(rnd.nextFloat() * 1000);
            product.setDimension(new Dimension(15, 30, 15));
            productsDao.save(product);
        }
        log.log(Level.INFO, "{0} Products created successfully", totalProducts);
    }

    public void createVehicals() {
        log.log(Level.INFO, "Trying to create {0} vehicles", totalVehicles);
        for (int i = 0; i < totalVehicles; i++) {
            TransportVehicle vehical = new TransportVehicle();
            vehical.setName("van - " + (i + 1));
            vehical.setNumber("MH " + (1000 + rnd.nextInt(8999)));
            vehical.setStatus(TransportVehicle.VehicalStatus.ACTIVE);
            vehical.setAvailableSpace(appSettings.getItemsPerVehicle());
            vehical.setDimension(new Dimension(75, 120, 15));
            transportVehicalDao.save(vehical);
        }
        log.log(Level.INFO, " {0} Vehicles created sucessfully", totalVehicles);
    }

    public void createOrders() {
        log.log(Level.INFO, "Trying to place {0} Dummy orders", totalOrders);
        for (int i = 0; i < totalOrders; i++) {
            OrderForm order = randomOrder();
            orderService.placeOrder(order);
        }
        log.log(Level.INFO, " {0} Dummy orders placed sucessfully", totalOrders);
    }

    /**
     * This method generates a random order
     *
     * @return
     */
    public static OrderForm randomOrder() {
        OrderForm order = new OrderForm();
        order.setComments("Random Comment " + rnd.nextInt(1000));
        order.setName("Order Name - " + rnd.nextInt(1000));
        int itemCount = 1 + rnd.nextInt(12); // generating random orders (minimum one item max 12 items)
        List<OrderForm.Item> items = new ArrayList<OrderForm.Item>();
        for (int j = 0; j < itemCount; j++) {
            items.add(new OrderForm.Item("sku_" + (1 + rnd.nextInt(totalProducts))));
        }
        order.setItems(items);
        return order;
    }
}
