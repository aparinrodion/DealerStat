package org.leverx.dealerstat.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trader_id")
    private Integer trader_id;
    @Column(name = "approved")
    private boolean approved;
    @Column(name = "message")
    private String message;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date created_at;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "author_email")
    private String author_email;
}
