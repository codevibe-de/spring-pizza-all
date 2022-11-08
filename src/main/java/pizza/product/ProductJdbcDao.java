package pizza.product;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Optional;

@Component
public class ProductJdbcDao implements ProductRepository, RowMapper<Product> {

    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Product save(Product product) {
        this.jdbcTemplate.update(
                "INSERT INTO products (pk, name, price) VALUES (?, ?, ?)",
                product.getProductId(), product.getName(), product.getPrice());
        return product;
    }

    @Override
    public boolean existsById(String productId) {
        var count = this.jdbcTemplate.queryForObject(
                "SELECT COUNT(p.*) FROM products p WHERE p.pk=?",
                Integer.class,
                productId);
        return (count != null && count == 1);
    }

    @Override
    public Collection<Product> findAll() {
        return this.jdbcTemplate.query(
                "SELECT p.* FROM products p",
                new Object[]{},
                new int[]{},
                this
        );
    }

    @Override
    public Optional<Product> findById(String productId) {
        try {
            Product p = this.jdbcTemplate.queryForObject(
                    "SELECT p.* FROM products p WHERE p.pk = ?",
                    new Object[]{productId},
                    new int[]{Types.VARCHAR},
                    this
            );
            return Optional.ofNullable(p);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getString("pk"),
                rs.getString("name"),
                rs.getDouble("price")
        );
    }
}
