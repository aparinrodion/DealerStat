package org.leverx.dealerstat.mappers;

import lombok.NoArgsConstructor;
import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.model.GameObject;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class GameObjectMapper {
    public GameObject mapToGameObject(GameObjectDto gameObjectDto) {
        GameObject gameObject = new GameObject();
        gameObject.setId(gameObjectDto.getId());
        gameObject.setTitle(gameObjectDto.getTitle());
        gameObject.setText(gameObjectDto.getText());
        gameObject.setStatus(gameObjectDto.getStatus());
        gameObject.setTraderId(gameObjectDto.getTraderId());
        gameObject.setCreatedAt(gameObjectDto.getCreatedAt());
        gameObject.setUpdatedAt(gameObjectDto.getUpdatedAt());
        gameObject.setGameId(gameObjectDto.getGameId());
        return gameObject;
    }

    public GameObjectDto mapToDto(GameObject gameObject) {
        GameObjectDto gameObjectDto = new GameObjectDto();
        gameObjectDto.setId(gameObject.getId());
        gameObjectDto.setTitle(gameObject.getTitle());
        gameObjectDto.setText(gameObject.getText());
        gameObjectDto.setStatus(gameObject.getStatus());
        gameObjectDto.setTraderId(gameObject.getTraderId());
        gameObjectDto.setCreatedAt(gameObject.getCreatedAt());
        gameObjectDto.setUpdatedAt(gameObject.getUpdatedAt());
        gameObjectDto.setGameId(gameObject.getGameId());
        return gameObjectDto;
    }
}
