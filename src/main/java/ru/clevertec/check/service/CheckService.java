package ru.clevertec.check.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.entity.response.CheckResponseDto;
import ru.clevertec.check.repository.CheckRepository;
import ru.clevertec.check.repository.DiscountCardRepository;
import ru.clevertec.check.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DiscountCardRepository discountCardRepository;

    @Autowired
    private CheckRepository checkRepository;

    public Check addProductToCheck(List<Long> productIds, List<Integer> quantities, int discountCardNumber, double balanceDebitCard) {

        Check check = new Check();
        check.setDiscountCard(discountCardRepository.findDiscountCardByNumber(discountCardNumber));
        check.setBalanceDebitCard(balanceDebitCard);

        if (check.getProducts() == null) {
            check.setProducts(new ArrayList<>());
        }

        double totalPriceWithDiscount = 0.0;
        totalPriceWithDiscount = Math.round(totalPriceWithDiscount * 100.0) / 100.0;

        double totalDiscount = 0.0;
        totalDiscount = roundHalfUp(totalDiscount, 2);

        double totalPriceWithoutDiscount = 0.0;
        totalPriceWithoutDiscount = roundHalfUp(totalPriceWithoutDiscount, 2);

        double totalProductPrice = 0.0;


        for (int i = 0; i < productIds.size(); i++) {
            Product product = productRepository.findById(productIds.get(i))
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            int quantity = quantities.get(i);

            double productPrice = product.getPrice();
            totalProductPrice = productPrice * quantity;
            product.setTotalProductPrice(totalProductPrice);
            totalPriceWithoutDiscount += totalProductPrice;

            DiscountCard discountCard = check.getDiscountCard();
            double productDiscount = 0.0;
            if (quantity >= 5 && product.isWholesale()) {
                productDiscount = totalProductPrice * 0.1;
            } else if (discountCard != null) {
                double personalDiscount = discountCard.getAmount() / 100.0;
                productDiscount = personalDiscount * totalProductPrice;
            }


            totalDiscount += productDiscount;
            totalDiscount = roundHalfUp(totalDiscount, 2);
            totalPriceWithDiscount += totalProductPrice - productDiscount;

            product.setQuantity(quantity);
            product.setTotalProductPrice(totalProductPrice);
            product.setPrice(productPrice);
            product.setDiscount(productDiscount);
            product.setQuantityInStock(product.getQuantityInStock() - quantity);
            product.setCheck(check);
            check.getProducts().add(product);
        }


        check.setTotalPriceWithoutDiscount(totalPriceWithoutDiscount);
        check.setTotalPriceWithDiscount(totalPriceWithDiscount);
        check.setTotalDiscount(totalDiscount);

        DiscountCard discountCard = check.getDiscountCard();
        assert discountCard != null;
        check.setDiscountCardAmount(discountCard.getAmount());
        check.setDiscountCardNumber(discountCardNumber);

        checkRepository.save(check);

        return check;
    }

    public CheckResponseDto getTotalCheckDetails(Long checkId) {
        Check check = checkRepository.findById(checkId)
                .orElseThrow(() -> new EntityNotFoundException("Check not found"));

        return new CheckResponseDto(check.getId(), check.getTotalPriceWithoutDiscount(), check.getTotalDiscount(), check.getTotalPriceWithDiscount(), check.getDiscountCard());
    }

    public List<Check> findAll() {
        return checkRepository.findAll();
    }

    public double roundHalfUp(double value, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        return Math.round(value * scale) / scale;
    }
}


