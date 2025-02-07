package br.com.xpeducacao.georgy.appvendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.xpeducacao.georgy.appvendas.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
