package kz.whydudeman.kazdream.project.services;

import kz.whydudeman.kazdream.project.forms.DefaultIdResponse;
import kz.whydudeman.kazdream.project.forms.request.CustomerForm;
import kz.whydudeman.kazdream.project.models.Customer;
import kz.whydudeman.kazdream.project.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private RabbitMQService rabbitMQService;

    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return getCustomerByIdOrThrow(id);

    }

    public DefaultIdResponse createCustomer(CustomerForm form) {
        Customer customer = new Customer();
        customer.setName(form.name);
        customer.setLastName(form.lastName);
        customer = customerRepo.save(customer);
        rabbitMQService.send(customer);
        return new DefaultIdResponse(HttpStatus.CREATED, customer.getId());
    }

    public DefaultIdResponse updateCustomer(Long id, CustomerForm form) {
        Customer customer = getCustomerByIdOrThrow(id);
        customer.setName(form.name);
        customer.setLastName(form.lastName);
        customer = customerRepo.save(customer);
        return new DefaultIdResponse(HttpStatus.CREATED, customer.getId());
    }

    public DefaultIdResponse deleteCustomer(Long id) {
        if (!customerRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CUSTOMER_NOT_FOUND");
        }
        customerRepo.deleteById(id);
        return new DefaultIdResponse(HttpStatus.OK, id);
    }

    private Customer getCustomerByIdOrThrow(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "CUSTOMER_NOT_FOUND"));
    }
}
