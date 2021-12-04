package org.leverx.dealerstat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Integer id;
    private Integer trader_id;
    private boolean approved;
    private String message;
    private Date created_at;
    private Integer rating;
    private String author_email;
}
