package ru.clevertec.check.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 50, columnDefinition = "VARCHAR")
    private String description;

    @Column(name = "price", columnDefinition = "DECIMAL")
    private double price;

    @Column(name = "quantity_in_stock", columnDefinition = "INTEGER")
    private int quantityInStock;

    @Column(name = "is_wholesale", columnDefinition = "BOOLEAN")
    private boolean isWholesale;

    @JsonIgnore
    private double discount;

    @JsonIgnore
    private double totalProductPrice;

    @JsonIgnore
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "check_id")
    @JsonIgnore
    private Check check;

}
