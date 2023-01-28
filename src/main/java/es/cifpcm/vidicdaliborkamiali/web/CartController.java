package es.cifpcm.vidicdaliborkamiali.web;

import es.cifpcm.vidicdaliborkamiali.dao.ProductsRepository;
import es.cifpcm.vidicdaliborkamiali.model.ProductOrder;
import es.cifpcm.vidicdaliborkamiali.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class CartController {
    @Autowired
    ProductsRepository productsRepository;

    List<ProductOrder> shoppingList = new ArrayList<>();
    @RequestMapping("/notStock")
    public String nostock() {
        return "products/notStock";
    }

    @RequestMapping("/products/shoppingcart")
    public String shoppingcart(Model model) {
        model.addAttribute("products", shoppingList);
        return "products/shoppingcart";
    }

    @RequestMapping("/deleteItem")
    public String deleteitem(@RequestParam Integer id) {

        Object pro = shoppingList.stream().filter(item -> (item.getId()).equals(id)).findFirst().orElse(null);
        shoppingList.remove(pro);

        return "redirect: products";
    }

    // Compra: setea los stocks de los item.

    @RequestMapping("/buy")
    public String buycart() {

        if(shoppingList.size()<=0){
            return "redirect: products";
        }

        for (ProductOrder item : shoppingList
        ) {
            Optional<Products> pro = productsRepository.findById(item.getId());
            if(pro==null) continue;

            pro.get().setProductStock(item.getProductStock()- item.getQuantity());
            productsRepository.save(pro.get());

        }
        shoppingList = new ArrayList<>();
        return "redirect: products";
    }
    // Cancelar la compra.
    @RequestMapping("/cancel")
    public String cancel() {

        shoppingList = new ArrayList<>();

        return "redirect: products";
    }

    @RequestMapping("products/shoppingcart/{id}")
    public String shoppingcart(@PathVariable Integer id, Model model) {

        Boolean repeated = false;
        Float total = 0f;
        Optional<Products> addToCartProduct = productsRepository.findById(id);

        if(addToCartProduct.get().getProductStock()==0){
            shoppingList = new ArrayList<>();
            return "products/notStock";
        }

            for (ProductOrder item: shoppingList) {
                if ((item.getId()).equals(addToCartProduct.get().getId())){
                    item.addQuantity();
                    repeated = true;
                }
            }

        if(!repeated) {
           ProductOrder addProduct = new ProductOrder();
            addProduct.setId(addToCartProduct.get().getId());
            addProduct.setProductName(addToCartProduct.get().getProductName());
            addProduct.setProductPrice(addToCartProduct.get().getProductPrice());
            addProduct.setProductPicture(addToCartProduct.get().getProductPicture());
            addProduct.setProductStock(addToCartProduct.get().getProductStock());
            addProduct.setQuantity(1);
            shoppingList.add(addProduct);
        }
        // Cantidad de dinero total

        for (ProductOrder item: shoppingList
        ) {

            total += item.getTotal();

            if (total==null){
                total = 0f;
            }
        }

        // No de error si la lista está vacia

        if (shoppingList.size()>0){
            model.addAttribute("products", shoppingList);
        }
        model.addAttribute("totalDe", total);
        return "products/shoppingcart";
    }

//    @GetMapping("/pdfFile")
//    public String getPdf(Model model){
//        model.addAttribute("products", shoppingList);
//        return "getPdfFile";
//    }

//    @GetMapping("/createPdf")
//    public String pdfFile(Model model){
//        String filePdf = "C:\\Users\\Dali\\DocumentsSamplePdfFile.pdf";
//        model.addAttribute("products", shoppingList);
//
//        try {
//            PdfWriter writer = new PdfWriter(filePdf);
//            PdfDocument pdfDoc = new PdfDocument(writer);
//            Document document = new Document(pdfDoc);
//            Paragraph p1 = new Paragraph("Hello Welcome To CS Academy");
//            float[] columnWidth = {200f, 200f, 200f};
//            Table table = new Table(columnWidth);
//            table.addCell(new Cell().add("ID"));
//            table.addCell(new Cell().add("Name"));
//            table.addCell(new Cell().add("Email"));
//            for(Products item: shoppingList){
//                table.addCell(new Cell().add(String.valueOf(item.getId())));
//                table.addCell(new Cell().add(item.getProductName()));
//                table.addCell(new Cell().add(String.valueOf(item.getProductPrice())));
//            }
//
//
//            document.add(p1);
//            document.add(table);
//            document.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/products";
//    }
}
