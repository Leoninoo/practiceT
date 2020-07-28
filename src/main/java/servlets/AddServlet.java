package servlets;

import dao.BookDao;
import dao.BookDaoJdbc;
import models.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    BookDao bookDao;

    //Подключение к базе данных
    @Override
    public void init() {
        String url = "jdbc:mysql://localhost:3306/practice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            bookDao = new BookDaoJdbc(connection);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Перенаправляет запрос на jsp страницу
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/add.jsp").forward(req, resp);
    }

    //Получает данные с формы на jsp странице и добавляет новую книгу в бд
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        String description = req.getParameter("description");
        String author = req.getParameter("author");

        if(name == null || price == 0 || description == null || author == null) {
            return;
        }

        Book book = new Book(name, price, description, author);
        bookDao.save(book);

        resp.sendRedirect(req.getContextPath() + "/main");
    }
}
