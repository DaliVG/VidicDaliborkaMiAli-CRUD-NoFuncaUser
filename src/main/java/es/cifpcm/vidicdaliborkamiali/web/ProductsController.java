package es.cifpcm.vidicdaliborkamiali.web;

import es.cifpcm.vidicdaliborkamiali.dao.ProductsRepository;
import es.cifpcm.vidicdaliborkamiali.dao.ProvinciaRepository;
import es.cifpcm.vidicdaliborkamiali.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductsController {
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    ProvinciaRepository provinciaRepository;

    @RequestMapping("/products")
    public String product(Model model) {

        // Mostrar el filtrado de los muebles por municipio

//        List<Products> AllProducts = productsRepository.findAll();
//        List<Products> FilteredProducts = new ArrayList<Products>();
//
//        for (Products munProduct: AllProducts) {
//
//            if (idMunicipio.equals(Products.getIdMunicipio())) {
//
//                FilteredProducts.add(munProduct);
//            }
//        }

        model.addAttribute("provincias", provinciaRepository.findAll());
        model.addAttribute("products", productsRepository.findAll());
        return "products/products";
    }

    @RequestMapping("/createProduct")
    public String create(Model model) {
        model.addAttribute("provincias", provinciaRepository.findAll());
        return "products/createProduct";
    }

    @RequestMapping("/saveProduct")
    public String save(@RequestParam String prodName, @RequestParam Float prodPrice, @RequestParam Integer prodStock, @RequestParam String prodImage) {
        Products product = new Products();
        product.setProductName(prodName);

        if (prodImage==""){
            prodImage="logo.png";
            product.setProductPicture(prodImage);
        } else {
            product.setProductPicture(prodImage);
        }
        product.setProductPrice(prodPrice);

        product.setIdMunicipio(35620);
        productsRepository.save(product);

        return "redirect: products/showProduct/" + product.getProductId();
    }

    @RequestMapping("products/showProduct/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productsRepository.findById(id).orElse(null));
        return "products/showProduct";
    }

    @RequestMapping("/deleteProduct")
    public String delete(@RequestParam Integer id) {
        Products product = productsRepository.findById(id).orElse(null);
        productsRepository.delete(product);

        return "redirect:/products";
    }

    @RequestMapping("products/showProduct/editProduct/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productsRepository.findById(id).orElse(null));
        return "products/editProduct";
    }

    @RequestMapping("/updateProduct")
    public String update(@RequestParam Integer id, @RequestParam String prodName, @RequestParam Float prodPrice, @RequestParam String prodImage) {
        Products product = productsRepository.findById(id).orElse(null);
        product.setProductName(prodName);
        if (prodImage==""){
            prodImage="logo.png";
            product.setProductPicture(prodImage);
        } else {
            product.setProductPicture(prodImage);
        }
        product.setProductPrice(prodPrice);
        productsRepository.save(product);

        return "redirect: products/showProduct/" + product.getProductId();
    }
}
