package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.GameDto;
import org.leverx.dealerstat.models.Game;

import java.util.List;

public interface GameService {
    GameDto save(GameDto gameDto);

    List<Game> getAll();

    GameDto getById(Integer id);
}
