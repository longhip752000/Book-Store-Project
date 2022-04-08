/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbv.cart;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author GF65
 */
public class Cart implements Serializable{
    private HashMap<String, Integer> items;

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }
    
    public void addToCart(String key) {
        if(items == null) {
            items = new HashMap<>();
        }
        
        int quantity = 1; // default
        
        if(items.containsKey(key)) {
            quantity = items.get(key) + 1;
        }
        
        items.put(key, quantity);
    }
    
    public boolean removeFromCart(String key) {
        if(items == null) {
            return false;
        }
        if(items.containsKey(key)) {
            items.remove(key);
            if(items.isEmpty()) {
                items = null;
            }
            return true;
        }
        return false;
    }
}
