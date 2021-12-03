package org.leverx.dealerstat.repositories;

import org.leverx.dealerstat.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsByEmail(String email);
}
