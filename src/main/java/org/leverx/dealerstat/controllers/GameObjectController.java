package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.models.GameObject;
import org.leverx.dealerstat.services.GameObjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameObjectController {
    private final GameObjectService gameObjectService;

    @PutMapping("/objects/{id}")
    public GameObjectDto editGameObject(@PathVariable Integer id,
                                        @RequestBody GameObjectDto gameObjectDto) {
        gameObjectDto.setId(id);
        return gameObjectService.save(gameObjectDto);
    }

    @PostMapping("/objects")
    public void saveGameObject(@RequestBody GameObjectDto gameObjectDto) {
        gameObjectService.save(gameObjectDto);
    }

    @GetMapping("/objects")
    public List<GameObject> getAll() {
        return gameObjectService.getAll();
    }
    

}
