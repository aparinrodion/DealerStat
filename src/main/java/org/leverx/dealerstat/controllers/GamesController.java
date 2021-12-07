package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameDto;
import org.leverx.dealerstat.models.Game;
import org.leverx.dealerstat.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GamesController {
    private final GameService gameService;

    @GetMapping()
    public List<GameDto> getAll() {
        return gameService.getAll();
    }

    @PostMapping()
    public void addGame(@RequestBody GameDto gameDto) {
        gameService.save(gameDto);
    }

    @PutMapping("/{id}")
    public void updateGame(@RequestBody GameDto gameDto, @RequestParam Integer id) {
        gameDto.setId(id);
        gameService.save(gameDto);
    }


}
