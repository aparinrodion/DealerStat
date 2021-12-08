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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GamesRepository gamesRepository;
    private final GameMapper gameMapper;

    @Override
    public GameDto save(GameDto gameDto) {
        if (!gamesRepository.existsByName(gameDto.getName())) {
            Game game = gameMapper.mapToGame(gameDto);
            game = gamesRepository.save(game);
            return gameMapper.mapToDto(game);
        } else {
            return gameMapper.mapToDto(gamesRepository.findByName(gameDto.getName()));
        }

    }

    @Override
    public List<GameDto> getAll() {
        List<Game> games = new ArrayList<>();
        gamesRepository.findAll().forEach(games::add);
        return games.stream().map(gameMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public GameDto getById(Integer id) {
        return gameMapper.mapToDto(gamesRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.valueOf(Game.class), id)));
    }
}
