package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameDto;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.mappers.GameMapper;
import org.leverx.dealerstat.models.Game;
import org.leverx.dealerstat.models.User;
import org.leverx.dealerstat.repositories.GamesRepository;
import org.leverx.dealerstat.services.GameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GamesRepository gamesRepository;
    private final GameMapper gameMapper;

    @Override
    public GameDto save(GameDto gameDto) {
        Game game = gameMapper.mapToGame(gameDto);
        game = gamesRepository.save(game);
        return gameMapper.mapToDto(game);

    }

    @Override
    public List<Game> getAll() {
        List<Game> games = new ArrayList<>();
        gamesRepository.findAll().forEach(games::add);
        return games;
    }

    @Override
    public GameDto getById(Integer id) {
        return gameMapper.mapToDto(gamesRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.valueOf(Game.class), id)));
    }
}
