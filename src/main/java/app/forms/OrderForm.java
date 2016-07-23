/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.forms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NavNag
 */
public class OrderForm {

    private String name;
    private String comments;
    private List<Item> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<String> getSkuList() {
        List<String> skuList = new ArrayList<String>();
        for (Item item : items) {
            skuList.add(item.getSku());
        }
        return skuList;
    }

    /**
     *
     */
    public static class Item {

        private String sku;

        public Item() {
        }

        public Item(String sku) {
            this.sku = sku;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

    }
}
