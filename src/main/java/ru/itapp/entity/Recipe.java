package ru.itapp.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "recipes", schema = "public")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Recipe.class)
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonView(View.Recipe.class)
    private String name;

    @Column(name = "kcal", nullable = false)
    @JsonView(View.Recipe.class)
    private Integer kcal;

    @Column(name = "proteins", nullable = false)
    @JsonView(View.Recipe.class)
    private Double proteins;

    @Column(name = "fats", nullable = false)
    @JsonView(View.Recipe.class)
    private Double fats;

    @Column(name = "carbohydrates", nullable = false)
    @JsonView(View.Recipe.class)
    private Double carbohydrates;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private RecipeCategory category;

    @OneToMany(mappedBy = "recipe")
    @ToString.Exclude
    Set<Ingredients> ingredients;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonView(View.Recipe.class)
    @ToString.Exclude
    private List<RecipeStep> steps;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
