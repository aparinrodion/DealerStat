package org.leverx.dealerstat.repositories;

import org.leverx.dealerstat.models.GameObject;
import org.springframework.data.repository.CrudRepository;

public interface GameObjectRepository extends CrudRepository<GameObject, Integer> {
}
