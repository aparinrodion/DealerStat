package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.dto.GameObjectsPaginationDto;

import java.security.Principal;
import java.util.List;

public interface GameObjectService {
    GameObjectDto save(GameObjectDto gameObjectDto, Principal principal);

    GameObjectDto save(GameObjectDto gameObjectDto);

    GameObjectDto deleteById(Integer id);

    GameObjectDto getById(Integer id);

    GameObjectDto updateIfPrincipalIsOwner(Integer id, GameObjectDto gameObjectDto, Principal principal);

    GameObjectDto deleteIfPrincipalIsOwner(Integer id, Principal principal);

    List<GameObjectDto> getAllByGameIdAndTraderId(GameObjectsPaginationDto gameObjectsPaginationDto);
}
