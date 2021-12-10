package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.GameDto;

import java.util.List;

public interface GameService {
    GameDto save(GameDto gameDto);

    List<GameDto> getAll();

    GameDto getById(Integer id);

    GameDto updateById(Integer id, GameDto gameDto);
}
