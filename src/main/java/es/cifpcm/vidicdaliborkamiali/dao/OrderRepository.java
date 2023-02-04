package es.cifpcm.vidicdaliborkamiali.dao;

import es.cifpcm.vidicdaliborkamiali.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}