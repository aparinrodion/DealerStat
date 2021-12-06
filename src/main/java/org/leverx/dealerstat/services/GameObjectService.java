package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.models.GameObject;

import java.security.Principal;
import java.util.List;

public interface GameObjectService {
    GameObjectDto save(GameObjectDto gameObjectDto);

    List<GameObject> getAll();

    GameObjectDto deleteById(Integer id);

    GameObjectDto getById(Integer id);

    GameObjectDto updateIfPrincipalIsOwner(GameObjectDto gameObjectDto, Principal principal);

    GameObjectDto deleteIfPrincipalIsOwner(GameObjectDto gameObjectDto, Principal principal);
}
