package ru.itapp.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "menus", schema = "public")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Menu.class)
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonView(View.Menu.class)
    private String name;

    @Column(name = "image", nullable = false)
    @JsonView(View.Menu.class)
    private String image;

    @Column(name = "description", nullable = false)
    @JsonView(View.Menu.class)
    private String description;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonView(View.MenuAllInfo.class)
    private List<Recipe> monday;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonView(View.MenuAllInfo.class)
    private List<Recipe> tuesday;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonView(View.MenuAllInfo.class)
    private List<Recipe> wednesday;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonView(View.MenuAllInfo.class)
    private List<Recipe> thursday;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonView(View.MenuAllInfo.class)
    private List<Recipe> friday;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonView(View.MenuAllInfo.class)
    private List<Recipe> saturday;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonView(View.MenuAllInfo.class)
    private List<Recipe> sunday;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
