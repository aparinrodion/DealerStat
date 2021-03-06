package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.dto.GameObjectsPaginationDto;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.exceptions.UserIsNotOwnerOfObjectException;
import org.leverx.dealerstat.mappers.GameObjectMapper;
import org.leverx.dealerstat.model.GameObject;
import org.leverx.dealerstat.repositories.GameObjectRepository;
import org.leverx.dealerstat.services.GameObjectService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
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
    public GameObjectDto save(GameObjectDto gameObjectDto, Principal principal) {
        gameObjectDto.setTraderId(userService.getByEmail(principal.getName()).getId());
        GameObject gameObject = gameObjectRepository.save(gameObjectMapper.mapToGameObject(gameObjectDto));
        gameObjectDto = gameObjectMapper.mapToDto(gameObject);
        return gameObjectDto;
    }

    @Override
    public GameObjectDto save(GameObjectDto gameObjectDto) {
        GameObject gameObject = gameObjectRepository.save(gameObjectMapper.mapToGameObject(gameObjectDto));
        return gameObjectMapper.mapToDto(gameObject);
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
                () -> new EntityNotFoundException(GameObject.class, id)));
    }

    @Override
    public GameObjectDto updateIfPrincipalIsOwner(Integer id, GameObjectDto gameObjectDto, Principal principal) {
        GameObject gameObject = gameObjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(GameObject.class, id));
        gameObjectDto.setCreatedAt(gameObject.getCreatedAt());
        gameObjectDto.setUpdatedAt(new Date());
        gameObjectDto.setTraderId(userService.getByEmail(principal.getName()).getId());
        boolean isOwner = isPrincipalOwner(gameObjectMapper.mapToDto(gameObject), principal);
        if (isOwner) {
            return gameObjectMapper.mapToDto(gameObjectRepository.save(
                    gameObjectMapper.mapToGameObject(gameObjectDto)));
        } else {
            throw new UserIsNotOwnerOfObjectException(principal.getName(),
                    gameObjectDto.getId());
        }

    }

    @Override
    public GameObjectDto deleteIfPrincipalIsOwner(Integer id, Principal principal) {
        GameObjectDto gameObjectDto = getById(id);
        if (isPrincipalOwner(gameObjectDto, principal)) {
            return deleteById(id);
        } else {
            throw new UserIsNotOwnerOfObjectException(principal.getName(), gameObjectDto.getId());
        }
    }

    @Override
    public List<GameObjectDto> getAllByGameIdAndTraderId(GameObjectsPaginationDto gameObjectsPaginationDto) {
        List<GameObject> gameObjects = getGameObjects(Optional.ofNullable(
                        gameObjectsPaginationDto.getTraderId()),
                Optional.ofNullable(gameObjectsPaginationDto.getGameId()),
                gameObjectsPaginationDto.getPageable());
        return gameObjects.stream()
                .map(gameObjectMapper::mapToDto)
                .collect(Collectors.toList());
    }


    private List<GameObject> getGameObjects(Optional<Integer> traderId,
                                            Optional<Integer> gameId, Pageable pageable) {
        if (traderId.isEmpty()) {
            if (gameId.isEmpty()) {
                return gameObjectRepository.findAll(pageable).stream().collect(Collectors.toList());
            } else {
                return gameObjectRepository.getAllByGameId(gameId.get(), pageable);
            }
        } else {
            if (gameId.isEmpty()) {
                return gameObjectRepository.getAllByTraderId(traderId.get(), pageable);
            } else {
                return gameObjectRepository.getAllByTraderIdAndGameId(traderId.get(),
                        gameId.get(), pageable);
            }
        }
    }


    private boolean isPrincipalOwner(GameObjectDto gameObjectDto, Principal principal) {
        UserDto userDto = userService.getByEmail(principal.getName());
        return gameObjectDto.getTraderId().equals(userDto.getId());
    }
}
