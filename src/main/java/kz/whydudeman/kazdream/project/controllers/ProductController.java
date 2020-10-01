package kz.whydudeman.kazdream.project.controllers;

import kz.whydudeman.kazdream.project.forms.DefaultIdResponse;
import kz.whydudeman.kazdream.project.forms.request.ProductForm;
import kz.whydudeman.kazdream.project.models.Product;
import kz.whydudeman.kazdream.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "all")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping(value = "{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public DefaultIdResponse createProduct(@RequestBody ProductForm productForm) {
        return productService.createProduct(productForm);
    }

    @PutMapping(value = "{id}")
    public DefaultIdResponse updateProducer(@PathVariable Long id, @RequestBody ProductForm productForm) {
        return productService.updateProduct(id, productForm);
    }

    @DeleteMapping(value = "{id}")
    public DefaultIdResponse deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
