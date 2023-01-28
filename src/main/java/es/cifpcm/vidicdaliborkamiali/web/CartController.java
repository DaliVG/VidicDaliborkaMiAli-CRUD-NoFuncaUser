package es.cifpcm.vidicdaliborkamiali.web;

import es.cifpcm.vidicdaliborkamiali.dao.ProductsRepository;
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


@Controller
public class CartController {
    @Autowired
    ProductsRepository productsRepository;

    List<Products> shoppingList = new ArrayList<>();
    @RequestMapping("/notStock")
    public String stock() {
        return "products/notStock";
    }

    @RequestMapping("products/shoppingcart")
    public String shoppingcart(Model model) {
        model.addAttribute("products", shoppingList);
        return "products/shoppingcart";
    }

    @RequestMapping("/deleteItem")
    public String shoppingcart(@RequestParam Integer id) {

        Products itemOut = productsRepository.findById(id).orElse(null);

        for (int i = 0; i < shoppingList.size(); i++) {

            if((shoppingList.get(i).getId()).equals(itemOut.getId())){
                shoppingList.remove(i);
            }
        }

        itemOut.setProductStock(itemOut.getProductStock()+1);
        productsRepository.save(itemOut);
        return "redirect: products";
    }

    @RequestMapping("products/shoppingcart/{id}")
    public String deleteItem(@PathVariable Integer id, Model model) {

        Products addToCartProduct = productsRepository.findById(id).orElse(null);
        shoppingList.add(addToCartProduct);
        if (addToCartProduct.getProductStock()!=0){
            addToCartProduct.setProductStock(addToCartProduct.getProductStock()-1);
            productsRepository.save(addToCartProduct);
        } else {
            Products itemOut = productsRepository.findById(id).orElse(null);

            itemOut.setProductStock(itemOut.getProductStock()+1);
            productsRepository.save(itemOut);

            return "products/notStock";
        }
        Double total = 0d;
        for (Products item: shoppingList
             ) {
            total += item.getProductPrice();
        }


        model.addAttribute("total", total);
        model.addAttribute("products", shoppingList);
        return "products/shoppingcart";
    }

    @GetMapping("/pdfFile")
    public String getPdf(Model model){
        model.addAttribute("products", shoppingList);
        return "getPdfFile";
    }

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
