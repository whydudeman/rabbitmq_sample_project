package kz.whydudeman.kazdream.project.controllers;

import kz.whydudeman.kazdream.project.forms.DefaultIdResponse;
import kz.whydudeman.kazdream.project.forms.request.CustomerForm;
import kz.whydudeman.kazdream.project.models.Customer;
import kz.whydudeman.kazdream.project.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "all")
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @GetMapping(value = "{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public DefaultIdResponse createCustomer(@RequestBody CustomerForm customerForm) {
        return customerService.createCustomer(customerForm);
    }

    @PutMapping(value = "{id}")
    public DefaultIdResponse updateCustomer(@PathVariable Long id, @RequestBody CustomerForm customerForm) {
        return customerService.updateCustomer(id, customerForm);
    }

    @DeleteMapping(value = "{id}")
    public DefaultIdResponse deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }

}
