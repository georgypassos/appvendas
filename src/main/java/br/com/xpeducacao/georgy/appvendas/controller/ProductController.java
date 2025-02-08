package br.com.xpeducacao.georgy.appvendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.xpeducacao.georgy.appvendas.model.Product;
import br.com.xpeducacao.georgy.appvendas.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public CollectionModel<EntityModel<Product>> findAll() {
        List<Product> products = productService.findAll();
        List<EntityModel<Product>> productModels = products.stream()
                .map(product -> EntityModel.of(product,
                        WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findById(product.getId()))
                                .withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findAll())
                                .withRel("products")))
                .toList();

        return CollectionModel.of(productModels,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> {
                    EntityModel<Product> productModel = EntityModel.of(product,
                            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findById(id))
                                    .withSelfRel(),
                            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findAll())
                                    .withRel("products"));
                    return ResponseEntity.ok(productModel);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PostMapping
    public EntityModel<Product> create(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        return EntityModel.of(createdProduct,
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findById(createdProduct.getId()))
                        .withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findAll())
                        .withRel("products"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> update(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.update(id, updatedProduct)
                .map(product -> {
                    EntityModel<Product> productModel = EntityModel.of(product,
                            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findById(id))
                                    .withSelfRel(),
                            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findAll())
                                    .withRel("products"));
                    return ResponseEntity.ok(productModel);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return productService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
}