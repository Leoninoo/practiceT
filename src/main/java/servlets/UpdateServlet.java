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

@WebServlet("/change")
public class UpdateServlet extends HttpServlet {
    BookDao bookDao;

    //Подключение к базе данных
    @Override
    public void init() {
        String url = "jdbc:mysql://localhost:3306/practice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String userName = "root";
        String password = "524710kleo";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            bookDao = new BookDaoJdbc(connection);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Получает id выбранной книги из url, находит книгу и отправляет ее на jsp страницу
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookDao.find(Integer.parseInt(id));
        req.setAttribute("book", book);

        req.getServletContext().getRequestDispatcher("/jsp/change.jsp").forward(req, resp);
    }

    //Получает новые данные с формы на jsp странице и id книги из url и обновляет данные книги в бд
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        String description = req.getParameter("description");
        String author = req.getParameter("author");
        int sold = Integer.parseInt(req.getParameter("sold"));

        //Если одно из полей пустое - обновляем страницу
        if(name == null || price == 0 || description == null || author == null) {
            resp.sendRedirect(req.getContextPath() + "/change");
            return;
        }

        //Обновляем книгу в бд
        Book book = new Book(name, price, description, author, sold);
        book.setId(Integer.parseInt(id));
        bookDao.update(book);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
