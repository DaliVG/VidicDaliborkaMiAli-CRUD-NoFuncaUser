package es.cifpcm.vidicdaliborkamiali.dao;

import es.cifpcm.vidicdaliborkamiali.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}