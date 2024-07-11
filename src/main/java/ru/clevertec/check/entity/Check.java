package ru.clevertec.check.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "product_check")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "check", cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "discount_card_id")
    private DiscountCard discountCard;

    @Column
    private double totalDiscount;

    @JsonIgnore
    @Column
    private double balanceDebitCard;

    @Column
    private double totalPriceWithDiscount;

    @Column
    private double totalPriceWithoutDiscount;

    @Column(name = "discountCard")
    private int discountCardNumber;

    @Column
    private double discountCardAmount;

}
