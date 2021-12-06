package org.leverx.dealerstat.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "created_at")
    @CreatedDate
    //@Temporal(TemporalType.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @Column(name = "confirmed")
    private boolean confirmed = false;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    //private Collection<Role> roles;

    @OneToMany(mappedBy = "trader_id", fetch = FetchType.EAGER)
    private Set<Comment> comments;
    //private Collection<Comment> comments;

    @OneToMany(mappedBy = "trader_id", fetch = FetchType.EAGER)
    private Set<GameObject> gameObjects;


}
