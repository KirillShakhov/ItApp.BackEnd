package ru.itapp.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles", schema = "public")
public class Role {

    @Transient
    public final static Role USER = new Role(1L, "USER");

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
}
