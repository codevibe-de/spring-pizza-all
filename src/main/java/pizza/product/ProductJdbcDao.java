package pizza.product;

import pizza.PersistenceException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ProductJdbcDao implements ProductRepository {

    public static final String INSERT_SQL = "INSERT INTO products (pk, name, price) VALUES (?, ?, ?)";
    public static final String SELECT_COUNT_SQL = "SELECT COUNT(p.*) FROM products p WHERE p.pk=?";
    public static final String SELECT_ALL_SQL = "SELECT p.* FROM products p";
    public static final String SELECT_ONE_SQL = "SELECT p.* FROM products p WHERE p.pk = ?";
    private final DataSource dataSource;

    public ProductJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Product save(Product product) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
                statement.setString(1, product.getProductId());
                statement.setString(2, product.getName());
                statement.setDouble(3, product.getPrice());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            rethrowSqlException(e);
        }
        return findById(product.getProductId())
                .orElseThrow(() -> new PersistenceException("Cannot find persisted product"));
    }


    @Override
    public boolean existsById(String productId) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_COUNT_SQL)) {
                statement.setString(1, productId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 1;
                }
            }
        } catch (SQLException e) {
            rethrowSqlException(e);
        }
        return false;
    }


    @Override
    public Collection<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL)) {
                    while (resultSet.next()) {
                        Product product = mapRow(resultSet, resultSet.getRow());
                        products.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            rethrowSqlException(e);
        }
        return products;
    }


    @Override
    public Optional<Product> findById(String productId) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ONE_SQL)) {
                statement.setString(1, productId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet, 1));
                }
            }
        } catch (SQLException e) {
            rethrowSqlException(e);
        }
        return Optional.empty();
    }


    private Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getString("pk"),
                rs.getString("name"),
                rs.getDouble("price")
        );
    }

    private void rethrowSqlException(SQLException e) throws PersistenceException {
        throw new PersistenceException(e);
    }
}
