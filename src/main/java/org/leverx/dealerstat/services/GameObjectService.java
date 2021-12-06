package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.dto.GameObjectsPaginationDto;
import org.leverx.dealerstat.models.GameObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface GameObjectService {
    GameObjectDto save(GameObjectDto gameObjectDto);

    List<GameObject> getAll(Pageable pageable);

    GameObjectDto deleteById(Integer id);

    GameObjectDto getById(Integer id);

    GameObjectDto updateIfPrincipalIsOwner(GameObjectDto gameObjectDto, Principal principal);

    GameObjectDto deleteIfPrincipalIsOwner(GameObjectDto gameObjectDto, Principal principal);

    List<GameObjectDto> getAllByGameIdAndTraderId(GameObjectsPaginationDto gameObjectsPaginationDto);
}
