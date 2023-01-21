package es.cifpcm.vidicdaliborkamiali.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productoffer")
public class Products {
    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_price", nullable = true)
    private Float productPrice;
    @Column(name = "product_picture", nullable = true)
    private String productPicture;
    @Column(name = "id_municipio", nullable = false)
    private Integer idMunicipio;
    @Column(name = "product_stock", nullable = false)
    private Integer productStock;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Products() {
    }
}