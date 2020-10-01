package kz.whydudeman.kazdream.project.repositories;

import kz.whydudeman.kazdream.project.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
