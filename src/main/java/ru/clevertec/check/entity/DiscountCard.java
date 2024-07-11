package ru.clevertec.check.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "discount_card")
public class DiscountCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "INTEGER")
    @Digits(integer = 4, fraction = 0, message = "Number should have at most 4 digits")
    private int number;

    @Column(name = "amount", columnDefinition = "SMALLINT")
    @Max(message = "Amount can not exceed 100", value = 100)
    @Min(message = "Amount should be positive", value = 0)
    private int amount;

}