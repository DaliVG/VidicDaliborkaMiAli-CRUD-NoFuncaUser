package es.cifpcm.vidicdaliborkamiali.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id", columnDefinition = "SMALLINT UNSIGNED not null")
    private Integer orderId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "list_products", nullable = false, length = 500)
    private String listProducts;

    @Column(name = "total_price", nullable = false)
    private Float totalPrice;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "customer_address", nullable = false, length = 400)
    private String customerAddress;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getListProducts() {
        return listProducts;
    }

    public void setListProducts(String listProducts) {
        this.listProducts = listProducts;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

}