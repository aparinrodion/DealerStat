package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.mappers.GameObjectMapper;
import org.leverx.dealerstat.models.GameObject;
import org.leverx.dealerstat.repositories.GameObjectRepository;
import org.leverx.dealerstat.services.GameObjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameObjectServiceImpl implements GameObjectService {
    private final GameObjectRepository gameObjectRepository;
    private final GameObjectMapper gameObjectMapper;

    @Override
    public GameObjectDto save(GameObjectDto gameObjectDto) {
        GameObject gameObject = gameObjectRepository.save(gameObjectMapper.mapToGameObject(gameObjectDto));
        gameObjectDto = gameObjectMapper.mapToDto(gameObject);
        return gameObjectDto;
    }

    @Override
    public List<GameObject> getAll() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjectRepository.findAll().forEach(gameObjects::add);
        return gameObjects;
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
}
