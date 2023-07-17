package kz.arabro.planogram.nomenclature.adapter.repository.model;

import jakarta.persistence.*;
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

    @Column(name = "code1c")
    private String code1C;

    @Column(name = "rus_name", nullable = false)
    private String rusName;

    @Column(name = "kaz_name", nullable = false)
    private String kazName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryDbModel category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private BrandDbModel brand;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private ProducerDbModel producer;

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

    @Column(name = "image_path", nullable = false)
    private String imagePath;

}
