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
public class JdbcProductRepository implements ProductRepository {

    public static final String INSERT_SQL = "INSERT INTO products (pk, name, price) VALUES (?, ?, ?)";
    public static final String SELECT_COUNT_SQL = "SELECT COUNT(p.*) FROM products p WHERE p.pk=?";
    public static final String SELECT_ALL_SQL = "SELECT p.* FROM products p";
    public static final String SELECT_ONE_SQL = "SELECT p.* FROM products p WHERE p.pk = ?";

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Product save(Product product) {
        this.jdbcTemplate.update(
                INSERT_SQL,
                product.getProductId(), product.getName(), product.getPrice()
        );
        return product;
    }

    @Override
    public boolean existsById(String productId) {
        var count = this.jdbcTemplate.queryForObject(
                SELECT_COUNT_SQL,
                Integer.class,
                productId
        );
        return (count != null && count == 1);
    }

    @Override
    public Collection<Product> findAll() {
        return this.jdbcTemplate.query(
                SELECT_ALL_SQL,
                new Object[]{},
                new int[]{},
                ProductRowMapper.INSTANCE
        );
    }

    @Override
    public Optional<Product> findById(String productId) {
        try {
            Product p = this.jdbcTemplate.queryForObject(
                    SELECT_ONE_SQL,
                    new Object[]{productId},
                    new int[]{Types.VARCHAR},
                    ProductRowMapper.INSTANCE
            );
            return Optional.ofNullable(p);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    //
    // --- inner classes ---
    //

    static class ProductRowMapper implements RowMapper<Product> {
        // singleton design pattern
        public static ProductRowMapper INSTANCE = new ProductRowMapper();

        private ProductRowMapper() {
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
}
