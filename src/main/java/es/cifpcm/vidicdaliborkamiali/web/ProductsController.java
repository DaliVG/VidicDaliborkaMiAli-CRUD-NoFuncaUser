package es.cifpcm.vidicdaliborkamiali.web;

import es.cifpcm.vidicdaliborkamiali.dao.ProductsRepository;
import es.cifpcm.vidicdaliborkamiali.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductsController {
    @Autowired
    ProductsRepository productsRepository;
    @RequestMapping("/products")
    public String product(Model model) {
        model.addAttribute("products", productsRepository.findAll());
        return "products/products";
    }

    @RequestMapping("/createProduct")
    public String create(Model model) {
        return "products/createProduct";
    }

    @RequestMapping("/saveProduct")
    public String save(@RequestParam String prodName, @RequestParam Float prodPrice, @RequestParam Integer prodStock, @RequestParam String prodImage) {
        Products product = new Products();
        product.setProductName(prodName);
        product.setProductPicture(prodImage);
        product.setProductPrice(prodPrice);
        product.setProductStock(prodStock);
        product.setIdMunicipio(35620);
        productsRepository.save(product);

        return "redirect: showProduct/" + product.getProductId();
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
        product.setProductPicture(prodImage);
        product.setProductPrice(prodPrice);
        productsRepository.save(product);

        return "redirect: products/showProduct/" + product.getProductId();
    }
}
