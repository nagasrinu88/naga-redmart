/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.scripts.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author NavNag
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    public Script script;

    @RequestMapping(value = "/setup")
    public String register() {
        try {
            script.createProducts();
            script.createVehicals();
            script.createOrders();
        } catch (Exception e) {
            return "already setup completed";
        }
        return "application is setup successfully";
    }
}
