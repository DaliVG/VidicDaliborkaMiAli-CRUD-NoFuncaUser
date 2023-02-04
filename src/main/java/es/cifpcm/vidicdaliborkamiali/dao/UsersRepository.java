package es.cifpcm.vidicdaliborkamiali.dao;

import es.cifpcm.vidicdaliborkamiali.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
}