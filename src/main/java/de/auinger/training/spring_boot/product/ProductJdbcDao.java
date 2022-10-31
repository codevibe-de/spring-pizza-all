package de.auinger.training.spring_boot.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class ProductJdbcDao implements ProductRepository, RowMapper<Product> {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Product save(Product product) {
        this.jdbcTemplate.update(
                "INSERT INTO products (id, name, price) VALUES (?, ?, ?)",
                new Object[]{product.getProductId(), product.getName(), product.getPrice()}
        );
        return product;
    }

    @Override
    public boolean existsById(String productId) {
        return false;
    }

    @Override
    public Collection<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(String productId) {
        return null;
    }

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getString("id"),
                rs.getString("name"),
                rs.getDouble("price")
        );
    }
}
