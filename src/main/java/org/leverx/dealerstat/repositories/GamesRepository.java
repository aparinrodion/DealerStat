package org.leverx.dealerstat.repositories;

import org.leverx.dealerstat.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends CrudRepository<Game, Integer> {
}