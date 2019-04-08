package org.hcqis.ventech.cloud.automation.demo;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class ProductService {
    @Autowired
	ProductRepository ProductRepository;

    public List<Product> findAll() {
        return ProductRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return ProductRepository.findById(id);
    }

    public Product save(Product stock) {
        return ProductRepository.save(stock);
    }

    public void deleteById(Long id) {
        ProductRepository.deleteById(id);
    }
}	