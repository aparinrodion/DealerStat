package org.leverx.dealerstat.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NewPasswordDto {
    @NotEmpty
    private String code;
    @NotEmpty
    private String password;
}
