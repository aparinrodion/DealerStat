package org.leverx.dealerstat.dto;

import lombok.Data;
import org.leverx.dealerstat.models.GameObjectStatus;

import java.util.Date;

@Data
public class GameObjectDto {
    private Integer id;
    private String title;
    private String text;
    private GameObjectStatus status;
    private Integer trader_id;
    private Date created_at;
    private Date updated_at;
    private Integer game_id;
}
