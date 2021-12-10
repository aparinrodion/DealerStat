package org.leverx.dealerstat.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GameObjectDto {

    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;

    private String status;

    private Integer traderId;

    private Date createdAt;

    private Date updatedAt;

    @NotNull
    private Integer gameId;
}
