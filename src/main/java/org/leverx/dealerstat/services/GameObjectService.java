package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.models.GameObject;

import java.util.List;

public interface GameObjectService {
    GameObjectDto save(GameObjectDto gameObjectDto);

    List<GameObject> getAll();

    GameObjectDto deleteById(Integer id);

    GameObjectDto getById(Integer id);
}
