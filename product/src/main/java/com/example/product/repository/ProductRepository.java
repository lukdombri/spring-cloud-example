package com.example.product.repository;

import com.example.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final RowMapper<Product> rowMapper = (p, i) -> new Product(p.getInt("id"), p.getString("name"), p.getString("details"));
    private final JdbcTemplate template;

    public Optional<Product> findById(Integer id) {
        List<Product> products = template.query("select * from product where id = ?", this.rowMapper, id);
        if (products.size() > 0) {
            return Optional.ofNullable(products.iterator().next());
        }
        return Optional.empty();
    }

    public Product update(Product product) {
        Assert.isTrue(product.getId() != null && product.getId() != 0, "Product id must be non-null");
        return (Product) template.execute("update product set name = ? where id = ?", (PreparedStatementCallback<Object>) preparedStatement -> {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.execute();
            return findById(product.getId()).get();
        });
    }

    public List<Product> findAll() {
        return template.query("select * from product", this.rowMapper);
    }

}
