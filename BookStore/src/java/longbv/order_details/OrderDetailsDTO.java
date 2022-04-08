/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbv.order_details;

/**
 *
 * @author GF65
 */
public class OrderDetailsDTO {
    private int id;
    private String bookId;
    private int quantity;
    private double price;
    private double total;

    public OrderDetailsDTO(int id, String bookId, int quantity, double price) {
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
        this.total = this.price * this.quantity;
    }

    public OrderDetailsDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
