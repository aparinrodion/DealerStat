package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameDto;
import org.leverx.dealerstat.services.GameService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public GameDto addGame(@RequestBody @Valid GameDto gameDto) {
        return gameService.save(gameDto);
    }

    @PutMapping("/{id}")
    public GameDto updateGame(@RequestBody @Valid GameDto gameDto, @PathVariable Integer id) {
        return gameService.updateById(id, gameDto);
    }
}
