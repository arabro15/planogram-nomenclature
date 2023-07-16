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
@Table(name = "product")
public class ProductDbModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "code1C")
    private String code1C;

    @Column(name = "rusName", nullable = false)
    private String rusName;

    @Column(name = "kazName", nullable = false)
    private String kazName;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "producer", nullable = false)
    private String producer;

    @Column(name = "barcode", nullable = false)
    private String barcode;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "height", nullable = false)
    private String height;

    @Column(name = "weight", nullable = false)
    private String weight;

    @Column(name = "length", nullable = false)
    private String length;

    @Column(name = "imagePath", nullable = false)
    private String imagePath;

}
