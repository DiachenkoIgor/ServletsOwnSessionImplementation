package DAO;

import Entities.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {
    Product selectProductById(long id) throws SQLException;
    ArrayList<Product> selectAllProducts() throws SQLException;
}
