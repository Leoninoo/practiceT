package dao;

import models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoJdbc implements BookDao {
    private Connection connection;

    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM book WHERE id = ?";

    private final String SQL_SELECT_ALL =
            "SELECT * FROM book";

    private final String SQL_INSERT_PRODUCT =
            "INSERT INTO book(name, price, description, author) VALUES (?, ?, ?, ?)";

    private final String SQL_UPDATE_PRODUCT =
            "UPDATE book SET price = ?, description = ?, author = ? WHERE (ID = ?)";

    private final String SQL_DELETE_PRODUCT =
            "DELETE FROM book WHERE id = ?";

    public BookDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Book find(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                String author = resultSet.getString("author");

                Book product = new Book(name, price, description, author);
                product.setId(id);

                return product;
            }

            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Book product) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PRODUCT);
            statement.setString(1, product.getBookName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setString(4, product.getAuthor());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book product) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
            statement.setInt(1, product.getPrice());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getAuthor());
            statement.setInt(4, product.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PRODUCT);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findAll() {
        try {
            List<Book> products = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                String author = resultSet.getString("author");

                Book product = new Book(name, price, description, author);
                product.setId(id);

                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
