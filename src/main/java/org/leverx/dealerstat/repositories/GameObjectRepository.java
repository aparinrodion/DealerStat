package org.leverx.dealerstat.repositories;

import org.leverx.dealerstat.model.GameObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameObjectRepository extends PagingAndSortingRepository<GameObject, Integer> {
    Page<GameObject> findAll(Pageable pageable);

    List<GameObject> getAllByTraderIdAndGameId(Integer traderId, Integer gameId, Pageable pageable);

    List<GameObject> getAllByTraderId(Integer traderId, Pageable pageable);

    List<GameObject> getAllByGameId(Integer gameId, Pageable pageable);
}
