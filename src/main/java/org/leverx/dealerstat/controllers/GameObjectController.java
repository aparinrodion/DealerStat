package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.dto.GameObjectsPaginationDto;
import org.leverx.dealerstat.model.GameObject;
import org.leverx.dealerstat.services.GameObjectService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameObjectController {
    private static final String SKIP = "0";
    private static final String LIMIT = "10";

    private final GameObjectService gameObjectService;
    private final UserService userService;

    @PostMapping("/objects")
    public void saveGameObject(@Valid @RequestBody GameObjectDto gameObjectDto, Principal principal) {
        gameObjectService.save(gameObjectDto, principal);
    }

    @GetMapping("/objects")
    public List<GameObjectDto> getAll(@RequestParam(defaultValue = SKIP) Integer skip,
                                      @RequestParam(defaultValue = LIMIT) Integer limit,
                                      @RequestParam(required = false) Integer traderId,
                                      @RequestParam(required = false) Integer gameId) {
        Pageable pageable = PageRequest.of(skip, limit);
        GameObjectsPaginationDto gameObjectsPaginationDto =
                GameObjectsPaginationDto.builder()
                        .traderId(traderId)
                        .gameId(gameId)
                        .pageable(pageable)
                        .build();
        return gameObjectService.getAllByGameIdAndTraderId(gameObjectsPaginationDto);
    }

    @GetMapping("/objects/{id}")
    public GameObjectDto get(@PathVariable Integer id) {
        return gameObjectService.getById(id);
    }

    @PutMapping("/objects/{id}")
    public GameObjectDto edit(@PathVariable Integer id, @RequestBody @Valid GameObjectDto gameObjectDto,
                              Principal principal) {
        gameObjectDto.setId(id);
        return gameObjectService.updateIfPrincipalIsOwner(id, gameObjectDto, principal);
    }

    @DeleteMapping("/objects/{id}")
    public GameObjectDto delete(@PathVariable Integer id, Principal principal) {
        return gameObjectService.deleteIfPrincipalIsOwner(id, principal);
    }

    @GetMapping("/objects/my")
    public List<GameObject> getMyGameObjects(Principal principal) {
        return userService.getGameObjectsByPrincipal(principal);
    }
}
