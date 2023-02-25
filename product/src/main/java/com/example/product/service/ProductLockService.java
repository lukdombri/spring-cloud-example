package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Service
@RequiredArgsConstructor
public class ProductLockService {

    private final LockRegistry lockRegistry;
    private final ProductRepository productRepository;

    public Product updateProductWithLock(Product product) throws InterruptedException {
        String key = Integer.toString(product.getId());
        Lock lock = lockRegistry.obtain(key);
        boolean lockAcquired = lock.tryLock(1, TimeUnit.SECONDS);

        if (lockAcquired) {
            try {
                doUpdateFor(product.getId(), product.getName());
                Thread.sleep(15000);
            } finally {
                lock.unlock();
            }
        }
        return productRepository.findById(product.getId()).get();
    }

    private void doUpdateFor(Integer id, String name) {
        productRepository
                .findById(id).ifPresent(p -> {
                    p.setName(name);
                    productRepository.update(p);
                });
    }
}
