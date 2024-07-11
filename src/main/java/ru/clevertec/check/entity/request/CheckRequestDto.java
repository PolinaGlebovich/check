package ru.clevertec.check.entity.request;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckRequestDto {
    private Long discountCard;
    private double balanceDebitCard;
    private int discountCardNumber;
    private List<ProductRequestDto> products;
}
