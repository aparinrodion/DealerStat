package org.leverx.dealerstat.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.mappers.GameMapper;
import org.leverx.dealerstat.model.Game;
import org.leverx.dealerstat.repositories.GamesRepository;
import org.leverx.dealerstat.services.GameService;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class GameServiceUnitTest {

    private final GamesRepository gamesRepository = Mockito.mock(GamesRepository.class);
    private final GameService gameService = new GameServiceImpl(gamesRepository, new GameMapper());

    @Test
    public void findByExistingGameId() {
        Game game = getExistingGame();
        when(gamesRepository.findById(game.getId())).thenReturn(Optional.of(game));
        Assert.assertEquals(new GameMapper().mapToDto(game), gameService.getById(game.getId()));
    }

    @Test
    public void findByNonExistingGameId() {
        Game game = getNonExistingGame();
        when(gamesRepository.findById(game.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> gameService.getById(game.getId()));
    }


    @Test
    public void saveNonExistingGame() {
        Game game = getNonExistingGame();
        when(gamesRepository.existsByName(game.getName())).thenReturn(false);
        when(gamesRepository.save(game)).thenReturn(game);
        GameMapper gameMapper = new GameMapper();
        assertEquals(gameMapper.mapToDto(game), gameService.save(gameMapper.mapToDto(game)));
    }


    @Test
    public void saveExistingGame() {
        Game game = getExistingGame();
        when(gamesRepository.existsByName(game.getName())).thenReturn(true);
        when(gamesRepository.save(game)).thenReturn(game);
        when(gamesRepository.findByName(game.getName())).thenReturn(game);
        GameMapper gameMapper = new GameMapper();
        assertEquals(gameMapper.mapToDto(game), gameService.save(gameMapper.mapToDto(game)));
    }


    @Test
    public void getAll() {
        List<Game> gameList = new ArrayList<>();
        gameList.add(getExistingGame());
        gameList.add(getNonExistingGame());
        when(gamesRepository.findAll()).then(invocation -> gameList);
        GameMapper gameMapper = new GameMapper();
        Assert.assertEquals(gameList.stream().map(gameMapper::mapToDto).collect(Collectors.toList()), gameService.getAll());
    }


    private Game getExistingGame() {
        return new Game(1, "existing game");
    }

    private Game getNonExistingGame() {
        return new Game(2, "non existing game");
    }

}