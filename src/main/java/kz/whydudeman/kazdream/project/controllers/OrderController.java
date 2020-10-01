package kz.whydudeman.kazdream.project.controllers;

import kz.whydudeman.kazdream.project.forms.DefaultIdResponse;
import kz.whydudeman.kazdream.project.forms.request.OrderForm;
import kz.whydudeman.kazdream.project.models.Order;
import kz.whydudeman.kazdream.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "{customer_id}")
    public List<Order> getOrderByCustomerId(@PathVariable(name = "customer_id") Long customerId) {
        return orderService.getAll(customerId);
    }

    @GetMapping(value = "{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public DefaultIdResponse createOrder(@RequestBody OrderForm orderForm) {
        return orderService.createOrder(orderForm);
    }
}
