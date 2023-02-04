package es.cifpcm.vidicdaliborkamiali.model;

public class ProductOrder extends Products{
    private Integer quantity;

    public void addQuantity() {
        this.quantity++;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getTotal() {
        return this.getProductPrice() * this.quantity;
    }


}
