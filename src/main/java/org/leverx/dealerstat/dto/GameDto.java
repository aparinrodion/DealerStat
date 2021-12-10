package org.leverx.dealerstat.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GameDto {
    private Integer id;
    @NotEmpty
    private String name;
}
