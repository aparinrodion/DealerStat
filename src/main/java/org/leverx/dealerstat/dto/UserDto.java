package org.leverx.dealerstat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.leverx.dealerstat.model.Comment;
import org.leverx.dealerstat.model.GameObject;
import org.leverx.dealerstat.model.Role;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;

    private Date createdAt;

    private boolean confirmed = false;

    private boolean approved = false;

    private Set<Role> roles;

    private Set<Comment> comments;

    private Set<GameObject> gameObjects;

    private Double rating = 0.0;
}
