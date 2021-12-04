package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.GameDto;
import org.leverx.dealerstat.models.Game;

import java.util.List;

public interface GameService {
    void save(GameDto gameDto);

    List<Game> getAll();

}
