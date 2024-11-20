import java.sql.SQLException;
import java.util.List;

public interface SuppliersProductsRepository {
    int create(ClassSuppliersProducts entry) throws SQLException;

    ClassSuppliersProducts findById(int id) throws SQLException;

    boolean update(ClassSuppliersProducts entry) throws SQLException;

    boolean delete(int id) throws SQLException;

    List<ClassSuppliersProducts> findAll() throws SQLException;

    boolean exists(int supplierId, int productId) throws SQLException;

    int getAvailableQuantity(int supplierId, int productId) throws SQLException;
}
