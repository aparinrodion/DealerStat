package org.leverx.dealerstat.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "game_objects")
@NoArgsConstructor
@AllArgsConstructor
public class GameObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;
    @Column(name = "status")
    private GameObjectStatus status;
    @Column(name = "trader_id")
    private Integer trader_id;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date created_at;
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date updated_at;
    @Column(name = "game_id")
    private Integer game_id;
}
