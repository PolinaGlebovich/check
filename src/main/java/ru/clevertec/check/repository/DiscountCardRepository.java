package ru.clevertec.check.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.check.entity.DiscountCard;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Long> {

    Optional<DiscountCard> findById(Long id);

    DiscountCard findDiscountCardByNumber(int number);

    List<DiscountCard> findAll();

}
