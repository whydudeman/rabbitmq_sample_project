package kz.whydudeman.kazdream.project.services;

import kz.whydudeman.kazdream.project.forms.DefaultIdResponse;
import kz.whydudeman.kazdream.project.forms.request.ProductForm;
import kz.whydudeman.kazdream.project.models.Product;
import kz.whydudeman.kazdream.project.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private RabbitMQService rabbitMQService;

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return getProductByIdOrThrow(id);

    }

    public DefaultIdResponse createProduct(ProductForm form) {
        Product product = new Product();
        product.setName(form.name);
        product.setDescription(form.description);
        product = productRepo.save(product);
        rabbitMQService.send(product);
        return new DefaultIdResponse(HttpStatus.CREATED, product.getId());
    }

    public DefaultIdResponse updateProduct(Long id, ProductForm form) {
        Product product = getProductByIdOrThrow(id);
        product.setName(form.name);
        product.setDescription(form.description);
        product = productRepo.save(product);
        return new DefaultIdResponse(HttpStatus.CREATED, product.getId());
    }

    public DefaultIdResponse deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PRODUCT_NOT_FOUND");
        }
        productRepo.deleteById(id);
        return new DefaultIdResponse(HttpStatus.OK, id);
    }

    private Product getProductByIdOrThrow(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "PRODUCT_NOT_FOUND"));
    }
}
