package ru.clevertec.check.entity.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequestDto {
    private Long id;
    private int quantity;
}
