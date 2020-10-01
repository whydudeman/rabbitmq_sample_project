package kz.whydudeman.kazdream.project.repositories;

import kz.whydudeman.kazdream.project.models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProducerRepo extends JpaRepository<Producer, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Producer c WHERE c.id = :id")
    boolean existsById(@Param("id") Long id);
}
