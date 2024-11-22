import java.util.List;

public interface CustomersRepositoryInterface {
    int create(ClassCustomers customer);
    ClassCustomers findById(int id);
    List<ClassCustomers> findAll();
    boolean update(ClassCustomers customer);
    boolean delete(int id);
    List<ClassCustomers> findByNameOrId(String namePart, Integer id);
}
