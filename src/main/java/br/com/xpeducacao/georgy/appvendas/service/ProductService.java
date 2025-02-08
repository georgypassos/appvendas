package br.com.xpeducacao.georgy.appvendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.xpeducacao.georgy.appvendas.model.Product;
import br.com.xpeducacao.georgy.appvendas.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return (this.productRepository.findAll());
    }

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    public Optional<Product> update(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    return productRepository.save(product);
                });
    }

    public Optional<Void> deleteById(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.deleteById(id);
                    return null; // Retorna void
                });
    }

    public Integer count() {
        return Math.toIntExact(this.productRepository.count());
    }
}
