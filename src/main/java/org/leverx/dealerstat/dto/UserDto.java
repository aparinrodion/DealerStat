package org.leverx.dealerstat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.leverx.dealerstat.models.Comment;
import org.leverx.dealerstat.models.GameObject;
import org.leverx.dealerstat.models.Role;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date createdAt;
    private boolean confirmed = false;
    private boolean approved = false;
    private Set<Role> roles;
    private Set<Comment> comments;
    private Set<GameObject> gameObjects;
    private Double rating = 0.0;

    public void countRating() {
        double numberOfComments = (long) comments.size();
        System.out.println(numberOfComments);
        System.out.println(comments);
        rating = 0.0;
        if (numberOfComments != 0) {
            comments.stream().filter(Comment::isApproved)
                    .collect(Collectors.toList()).forEach(comment -> rating += comment.getRating());
            System.out.println(rating);
            rating /= numberOfComments;
        } else {
            rating = 0.0;
        }
    }
}
