//package ru.clevertec.check.entity.factory;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Component;
//import ru.clevertec.check.entity.Check;
//import ru.clevertec.check.entity.DiscountCard;
//import ru.clevertec.check.entity.Product;
//
//import java.util.List;
//
//@Component
//public class EntityFactory {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public Check createCheck(List<Product> products, DiscountCard discountCard,
//                             double totalDiscount, double balanceDebitCard,
//                             double totalPriceWithDiscount, double totalPriceWithoutDiscount,
//                             int discountCardNumber, double discountCardAmount) {
//
//        Check check = new Check();
//        check.setProducts(products);
//        check.setDiscountCard(discountCard);
//        check.setTotalDiscount(totalDiscount);
//        check.setBalanceDebitCard(balanceDebitCard);
//        check.setTotalPriceWithDiscount(totalPriceWithDiscount);
//        check.setTotalPriceWithoutDiscount(totalPriceWithoutDiscount);
//        check.setDiscountCardNumber(discountCardNumber);
//        check.setDiscountCardAmount(discountCardAmount);
//
//        entityManager.persist(check);
//
//        return check;
//    }
//
//    @Transactional
//    public DiscountCard createDiscountCard(int number, int amount) {
//        DiscountCard discountCard = new DiscountCard();
//        discountCard.setNumber(number);
//        discountCard.setAmount(amount);
//
//        entityManager.persist(discountCard);
//
//        return discountCard;
//    }
//
//    @Transactional
//    public Product createProduct(String description, double price, int quantityInStock,
//                                 boolean isWholesale, double discount, double totalProductPrice,
//                                 int quantity, Check check) {
//
//        Product product = new Product();
//        product.setDescription(description);
//        product.setPrice(price);
//        product.setQuantityInStock(quantityInStock);
//        product.setWholesale(isWholesale);
//        product.setDiscount(discount);
//        product.setTotalProductPrice(totalProductPrice);
//        product.setQuantity(quantity);
//        product.setCheck(check);
//
//        entityManager.persist(product);
//
//        return product;
//    }
//}