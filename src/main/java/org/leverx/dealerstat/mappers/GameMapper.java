package org.leverx.dealerstat.mappers;

import lombok.NoArgsConstructor;
import org.leverx.dealerstat.dto.GameDto;
import org.leverx.dealerstat.models.Game;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
@NoArgsConstructor
public class GameMapper {
    public Game mapToGame(GameDto gameDto) {
        Game game = new Game();
        game.setId(gameDto.getId());
        game.setName(gameDto.getName());
        return game;
    }

    public GameDto mapToDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setName(game.getName());
        return gameDto;
    }

}
