/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author NavNag
 */
@Controller
public class AppController {

    @RequestMapping(value = "/register")
    public String register() {
        return "home";
    }
}
