package com.mercadolivre.desafio_quality.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propName;
    @ManyToOne
    private District district;
    @OneToMany
    private List<Room> rooms;

    public Ground(String propName, District district, List<Room> rooms) {
        this.propName = propName;
        this.rooms = rooms;
        this.district = district;
    }
}
