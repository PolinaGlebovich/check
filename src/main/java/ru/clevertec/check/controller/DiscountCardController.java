package ru.clevertec.check.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.check.csvWriter.DiscountCardWriter;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.service.DiscountCardService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/discount_card")
public class DiscountCardController {

    @Autowired
    private DiscountCardService discountCardService;

    @Autowired
    private DiscountCardWriter discountCardWriter;

    @GetMapping
    public List<DiscountCard> getAllDiscountCards() {
        return discountCardService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DiscountCard>> getDiscountCardById(@PathVariable Long id) {
        Optional<DiscountCard> discountCard = discountCardService.findById(id);
        return ResponseEntity.ok().body(discountCard);
    }

    @PostMapping
    public ResponseEntity<DiscountCard> createDiscountCard(@RequestBody DiscountCard discountCard) {
        DiscountCard createdDiscountCard = discountCardService.save(discountCard);
        discountCardWriter.writeDiscountCardToCsv(createdDiscountCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiscountCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountCard> updateDiscountCard(@PathVariable Long id, @RequestBody DiscountCard updatedDiscountCard) {
        try {
            DiscountCard discountCard = discountCardService.update(id, updatedDiscountCard);
            return new ResponseEntity<>(discountCard, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        discountCardService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
