package org.leverx.dealerstat.dto;

import lombok.Data;
import org.leverx.dealerstat.models.Comment;
import org.leverx.dealerstat.models.Role;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
public class UserDto {
    private int id;
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private Date created_at;
    private boolean confirmed = false;
    private Set<Role> roles;
    private Set<Comment> comments;
}
