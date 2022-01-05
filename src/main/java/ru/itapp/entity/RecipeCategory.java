package ru.itapp.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "categories", schema = "public")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    @JsonView(View.Recipe.class)
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonView(View.Category.class)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Recipe> recipes;


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
