package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.dto.GameObjectsPaginationDto;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.exceptions.UserIsNotOwnerOfObjectException;
import org.leverx.dealerstat.mappers.GameObjectMapper;
import org.leverx.dealerstat.models.GameObject;
import org.leverx.dealerstat.repositories.GameObjectRepository;
import org.leverx.dealerstat.services.GameObjectService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameObjectServiceImpl implements GameObjectService {
    private final GameObjectRepository gameObjectRepository;
    private final GameObjectMapper gameObjectMapper;
    private final UserService userService;

    @Override
    public GameObjectDto save(GameObjectDto gameObjectDto) {
        GameObject gameObject = gameObjectRepository.save(gameObjectMapper.mapToGameObject(gameObjectDto));
        gameObjectDto = gameObjectMapper.mapToDto(gameObject);
        return gameObjectDto;
    }

    @Override
    public List<GameObjectDto> getAll(Pageable pageable) {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjectRepository.findAll(pageable).forEach(gameObjects::add);
        return gameObjects.stream().map(gameObjectMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public GameObjectDto deleteById(Integer id) {
        GameObjectDto gameObjectDto = getById(id);
        gameObjectRepository.deleteById(id);
        return gameObjectDto;
    }

    @Override
    public GameObjectDto getById(Integer id) {
        return gameObjectMapper.mapToDto(gameObjectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(GameObject.class), id)));
    }

    @Override
    public GameObjectDto updateIfPrincipalIsOwner(GameObjectDto gameObjectDto, Principal principal) {
        if (isPrincipalOwner(gameObjectDto, principal)) {
            return save(gameObjectDto);
        } else {
            throw new UserIsNotOwnerOfObjectException(principal.getName(), gameObjectDto.getId());
        }

    }

    @Override
    public GameObjectDto deleteIfPrincipalIsOwner(GameObjectDto gameObjectDto, Principal principal) {
        if (isPrincipalOwner(gameObjectDto, principal)) {
            return deleteById(gameObjectDto.getId());
        } else {
            throw new UserIsNotOwnerOfObjectException(principal.getName(), gameObjectDto.getId());
        }
    }

    @Override
    public List<GameObjectDto> getAllByGameIdAndTraderId(GameObjectsPaginationDto gameObjectsPaginationDto) {
        List<GameObject> gameObjects = getGameObjects(Optional.ofNullable(gameObjectsPaginationDto.getTraderId()),
                Optional.ofNullable(gameObjectsPaginationDto.getGameId())
                , gameObjectsPaginationDto.getPageable());
        return gameObjects.stream().map(gameObjectMapper::mapToDto).collect(Collectors.toList());
    }


    private List<GameObject> getGameObjects(Optional<Integer> trader_id,
                                            Optional<Integer> game_id, Pageable pageable) {
        if (trader_id.isEmpty()) {
            if (game_id.isEmpty()) {
                return gameObjectRepository.findAll(pageable).stream().collect(Collectors.toList());
            } else {
                return gameObjectRepository.getAllByGameId(game_id.get(), pageable);
            }
        } else {
            if (game_id.isEmpty()) {
                return gameObjectRepository.getAllByTraderId(trader_id.get(), pageable);
            } else {
                return gameObjectRepository.getAllByTraderIdAndGameId(trader_id.get(),
                        game_id.get(), pageable);
            }
        }
    }


    private boolean isPrincipalOwner(GameObjectDto gameObjectDto, Principal principal) {
        UserDto userDto = userService.getByEmail(principal.getName());
        return gameObjectDto.getTraderId().equals(userDto.getId());
    }
}
