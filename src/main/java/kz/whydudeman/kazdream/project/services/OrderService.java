package kz.whydudeman.kazdream.project.services;

import kz.whydudeman.kazdream.project.forms.DefaultIdResponse;
import kz.whydudeman.kazdream.project.forms.request.OrderForm;
import kz.whydudeman.kazdream.project.models.Customer;
import kz.whydudeman.kazdream.project.models.Order;
import kz.whydudeman.kazdream.project.models.Product;
import kz.whydudeman.kazdream.project.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RabbitMQService rabbitMQService;

    public List<Order> getAll(Long customerId) {
        return orderRepo.getAllByCustomer_Id(customerId);
    }

    public Order getOrderById(Long id) {
        return getOrderByIdOrThrow(id);

    }

    public DefaultIdResponse createOrder(OrderForm form) {
        Customer customer = customerService.getCustomerById(form.customerId);
        Product product = productService.getProductById(form.productId);
        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order = orderRepo.save(order);
        rabbitMQService.send(order);
        return new DefaultIdResponse(HttpStatus.CREATED, order.getId());
    }


    private Order getOrderByIdOrThrow(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ORDER_NOT_FOUND"));
    }
}
