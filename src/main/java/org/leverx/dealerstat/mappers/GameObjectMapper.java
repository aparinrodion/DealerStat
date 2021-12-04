package org.leverx.dealerstat.mappers;

import lombok.NoArgsConstructor;
import org.leverx.dealerstat.dto.GameObjectDto;
import org.leverx.dealerstat.models.GameObject;
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
        gameObject.setTrader_id(gameObjectDto.getTrader_id());
        gameObject.setCreated_at(gameObjectDto.getCreated_at());
        gameObject.setUpdated_at(gameObjectDto.getUpdated_at());
        gameObject.setGame_id(gameObjectDto.getGame_id());
        return gameObject;
    }

    public GameObjectDto mapToDto(GameObject gameObject) {
        GameObjectDto gameObjectDto = new GameObjectDto();
        gameObjectDto.setId(gameObject.getId());
        gameObjectDto.setTitle(gameObject.getTitle());
        gameObjectDto.setText(gameObject.getText());
        gameObjectDto.setStatus(gameObject.getStatus());
        gameObjectDto.setTrader_id(gameObject.getTrader_id());
        gameObjectDto.setCreated_at(gameObject.getCreated_at());
        gameObjectDto.setUpdated_at(gameObject.getUpdated_at());
        gameObjectDto.setGame_id(gameObject.getGame_id());
        return gameObjectDto;
    }
}
