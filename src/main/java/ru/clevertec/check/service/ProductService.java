package ru.clevertec.check.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        if (productRepository.existsByDescription(product.getDescription())) {
            throw new EntityExistsException("Product is already exists");
        }
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found")));
    }

    public Product update(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantityInStock(updatedProduct.getQuantityInStock());
        existingProduct.setWholesale(updatedProduct.isWholesale());
        return productRepository.save(existingProduct);
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }
}
