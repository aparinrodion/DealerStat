package org.leverx.dealerstat.repositories;

import org.leverx.dealerstat.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    boolean existsById(Integer id);

    List<User> getAllByApproved(boolean approved, Pageable pageable);
}
