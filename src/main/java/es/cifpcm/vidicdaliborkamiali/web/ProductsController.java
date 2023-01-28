package es.cifpcm.vidicdaliborkamiali.web;
import es.cifpcm.vidicdaliborkamiali.web.CartController;
import es.cifpcm.vidicdaliborkamiali.dao.MunicipioRepository;
import es.cifpcm.vidicdaliborkamiali.dao.ProductsRepository;
import es.cifpcm.vidicdaliborkamiali.dao.ProvinciaRepository;
import es.cifpcm.vidicdaliborkamiali.model.Municipio;
import es.cifpcm.vidicdaliborkamiali.model.Products;
import es.cifpcm.vidicdaliborkamiali.model.Provincia;
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
    @Autowired
    private MunicipioRepository municipioRepository;

    @RequestMapping("/products")
    public String product(Model model, @RequestParam(name="munis", required = false, defaultValue="0")Integer munis) {

        if(munis==0){
            model.addAttribute("products", productsRepository.findAll());
        } else {

            List<Products> AllProducts = productsRepository.findAll();
            List<Products> filteredProducts = new ArrayList<Products>();

            for (Products munProduct: AllProducts) {

                if (munis.equals(munProduct.getIdMunicipio())) {

                    filteredProducts.add(munProduct);
                }

            }

            model.addAttribute("products", filteredProducts);
        }
        model.addAttribute("provincias", provinciaRepository.findAll());
        return "products/products";
    }


    @RequestMapping("/createProduct")
    public String create(Model model) {
        model.addAttribute("provincias", provinciaRepository.findAll());
        return "products/createProduct";
    }



    @RequestMapping("/saveProduct")
    public String save(@RequestParam String prodName, @RequestParam Float prodPrice, @RequestParam Integer prodStock, @RequestParam Integer prodMun, @RequestParam String prodImage) {
        Products product = new Products();
        product.setProductName(prodName);

        if (prodImage==""){
            prodImage="logo.png";
            product.setProductPicture(prodImage);
        } else {
            product.setProductPicture(prodImage);
        }
        product.setProductPrice(prodPrice);
        product.setProductStock(prodStock);
        product.setIdMunicipio(prodMun);
        productsRepository.save(product);

        return "redirect: products/showProduct/" + product.getId();
    }

    @RequestMapping("products/showProduct/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productsRepository.findById(id).orElse(null));
        return "products/showProduct";
    }
    @RequestMapping("products/showProduct/editProduct/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productsRepository.findById(id).orElse(null));
        return "products/editProduct";
    }
    @RequestMapping("/deleteProduct")
    public String delete(@RequestParam Integer id) {
        Products product = productsRepository.findById(id).orElse(null);
        productsRepository.delete(product);

        return "redirect:/products";
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

        return "redirect: products/showProduct/" + product.getId();
    }
}
