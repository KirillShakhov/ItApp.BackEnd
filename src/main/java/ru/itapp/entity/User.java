package ru.itapp.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "username", unique = true, nullable = false)
    @JsonView(View.User.class)
    private String username;

    @Column(name = "active", nullable = false)
    @JsonView(View.User.class)
    private boolean active = false;

    @Column(name = "email", unique = true, nullable = false)
    @JsonView(View.User.class)
    private String email;


//    @OneToOne
//    @JoinColumn(name = "user_profile_ID")
//    private Profile userProfile;

    @Column(name = "pass", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_and_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles;


//    @Column(name = "registration_date", nullable = true)
//    @Temporal(TemporalType.DATE)
//    @JsonView(View.User.class)
//    private Date registration_date = java.util.Calendar.getInstance().getTime();

    public User(String login, String email, String pass) {
        this.username = login;
        this.email = email;
        this.password = pass;
    }

    public User(User user){
        active = user.active;
        username = user.username;
        email = user.email;
        password = user.password;
        roles = user.getRoles();
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
}
