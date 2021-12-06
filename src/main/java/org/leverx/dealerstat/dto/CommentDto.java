package org.leverx.dealerstat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Integer id;
    private Integer traderId;
    private boolean approved;
    private String message;
    private Date createdAt;
    private Integer rating;
    private String authorEmail;
}
