package org.leverx.dealerstat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private int id;
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private Date created_at;
    private boolean confirmed = false;
}
