package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameDto;
import org.leverx.dealerstat.models.Game;
import org.leverx.dealerstat.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GamesController {
    private final GameService gameService;

    @PostMapping
    public void save(@RequestBody GameDto gameDto) {
        gameService.save(gameDto);
    }

    @GetMapping
    public List<Game> getAll() {
        return gameService.getAll();
    }

    @PutMapping("/{id}")
    public GameDto updateGame(@PathVariable Integer id, @RequestBody GameDto gameDto) {
        gameDto.setId(id);
        gameDto = gameService.save(gameDto);
        return gameDto;
    }

}
