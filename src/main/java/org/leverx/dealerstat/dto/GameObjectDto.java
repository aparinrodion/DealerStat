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
    private Integer traderId;
    private Date createdAt;
    private Date updatedAt;
    private Integer gameId;
}
