package ru.clevertec.check.entity.response;

import lombok.*;
import ru.clevertec.check.entity.DiscountCard;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckResponseDto {
    private Long id;
    private double totalPrice;
    private double totalDiscount;
    private double finalPrice;
    private DiscountCard discountCard;
}