package ru.itapp.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @Column(name = "username", unique = true, nullable = false)
    @JsonView(View.User.class)
    private String username;

    @Column(name = "active", unique = true, nullable = false)
    @JsonView(View.User.class)
    private boolean active;

    @Column(name = "email", unique = true, nullable = false)
    @JsonView(View.User.class)
    private String email;


    @Column(name = "pass", nullable = false)
    private String pass;

    @ManyToMany
    private HashSet<Role> roles;


    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonView(View.User.class)
    private Date registration_date;

    public User(String login, String email, String pass) {
        this.username = login;
        this.email = email;
        this.pass = pass;
        this.registration_date = java.util.Calendar.getInstance().getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return username != null && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setActive(boolean b) {

    }
}
