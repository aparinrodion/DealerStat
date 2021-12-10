package org.leverx.dealerstat.repositories;

import org.leverx.dealerstat.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends CrudRepository<Game, Integer> {
    boolean existsByName(String name);

    Game findByName(String name);
}
