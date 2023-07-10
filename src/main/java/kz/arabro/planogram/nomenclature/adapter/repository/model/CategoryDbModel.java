package kz.arabro.planogram.nomenclature.adapter.repository.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryDbModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "parentID", nullable = false)
    private UUID parentID;
}
