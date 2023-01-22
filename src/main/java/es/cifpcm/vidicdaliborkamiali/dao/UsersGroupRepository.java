package es.cifpcm.vidicdaliborkamiali.dao;

import es.cifpcm.vidicdaliborkamiali.model.UsersGroup;
import es.cifpcm.vidicdaliborkamiali.model.UsersGroupId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersGroupRepository extends JpaRepository<UsersGroup, UsersGroupId> {
}