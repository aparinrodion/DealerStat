package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.dto.GameObjectsPaginationDto;
import org.leverx.dealerstat.models.GameObject;
import org.leverx.dealerstat.services.GameObjectService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameObjectController {
    private final GameObjectService gameObjectService;
    private final UserService userService;

    @PostMapping("/objects")
    public void saveGameObject(@RequestBody GameObjectDto gameObjectDto) {
        gameObjectService.save(gameObjectDto);
    }

    @GetMapping("/objects")
    public List<GameObjectDto> getAll(@RequestParam(defaultValue = "0") Integer skip,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam(required = false) Integer traderId,
                                      @RequestParam(required = false) Integer gameId) {
        Pageable pageable = PageRequest.of(skip, limit);
        GameObjectsPaginationDto gameObjectsPaginationDto =
                GameObjectsPaginationDto.builder()
                        .traderId(traderId)
                        .gameId(gameId)
                        .pageable(pageable).build();
        return gameObjectService.getAllByGameIdAndTraderId(gameObjectsPaginationDto);
    }

    @GetMapping("/objects/{id}")
    public GameObjectDto get(@PathVariable Integer id) {
        return gameObjectService.getById(id);
    }

    @PutMapping("/objects/{id}")
    public GameObjectDto edit(@PathVariable Integer id, @RequestBody GameObjectDto gameObjectDto,
                              Principal principal) {
        gameObjectDto.setId(id);
        return gameObjectService.updateIfPrincipalIsOwner(gameObjectDto, principal);
    }

    @DeleteMapping("/objects/{id}")
    public GameObjectDto delete(@PathVariable Integer id, @RequestBody GameObjectDto gameObjectDto,
                                Principal principal) {
        gameObjectDto.setId(id);
        return gameObjectService.deleteIfPrincipalIsOwner(gameObjectDto, principal);
    }


    @GetMapping("/objects/my")
    public List<GameObject> getMyGameObjects(Principal principal) {
        return userService.getPrincipalGameObjects(principal);
    }

}
