package kz.whydudeman.kazdream.project.repositories;

import kz.whydudeman.kazdream.project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
