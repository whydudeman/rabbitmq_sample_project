package kz.whydudeman.kazdream.project.repositories;

import kz.whydudeman.kazdream.project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> getAllByCustomer_Id(Long customerId);
}
