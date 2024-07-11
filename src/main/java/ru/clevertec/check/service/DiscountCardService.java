package ru.clevertec.check.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.repository.DiscountCardRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountCardService {

    private final DiscountCardRepository discountCardRepository;

    public DiscountCard save(DiscountCard discountCard) {
        return discountCardRepository.save(discountCard);
    }

    public Optional<DiscountCard> findById(Long id) {
        return Optional.ofNullable(discountCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discount card with id " + id + " not found")));
    }


    public List<DiscountCard> findAll() {
        return discountCardRepository.findAll();
    }

    public DiscountCard update(Long id, DiscountCard updatedDiscountCard) {
        DiscountCard existingDiscountCard = discountCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discount card with id " + id + " not found"));
        existingDiscountCard.setNumber(updatedDiscountCard.getNumber());
        existingDiscountCard.setAmount(updatedDiscountCard.getAmount());
        return discountCardRepository.save(updatedDiscountCard);
    }

    public void delete(Long id) {
        if (!discountCardRepository.existsById(id)) {
            throw new EntityNotFoundException("DiscountCard with id " + id + " not found");
        }
        discountCardRepository.deleteById(id);
    }
}
