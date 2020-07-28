package dao;

import models.Book;

import java.util.List;

public interface BookDao {
    Book find(int id);
    void save(Book model);
    void update(Book model);
    void delete(int id);

    List<Book> findAll();
}
