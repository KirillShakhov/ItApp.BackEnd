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
@Table(name = "recipes", schema = "public")
public class RecipeStep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.RecipeStep.class)
    private Long id;

    @Column(name = "image", nullable = false)
    @JsonView(View.RecipeStep.class)
    private String image;

    @Lob
    @Column(name = "text", nullable = false)
    @JsonView(View.RecipeStep.class)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe")
    @ToString.Exclude
    private Recipe recipe;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
