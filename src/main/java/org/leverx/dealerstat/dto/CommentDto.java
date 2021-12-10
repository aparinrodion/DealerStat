package org.leverx.dealerstat.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CommentDto {

    private Integer id;

    private boolean approved;

    private Date createdAt;

    private Integer traderId;

    @Min(0)
    @Max(10)
    @NotNull
    private Integer rating;
    @NotEmpty(message = "authorEmail")
    private String authorEmail;
    @NotEmpty(message = "mes")
    private String message;
}
