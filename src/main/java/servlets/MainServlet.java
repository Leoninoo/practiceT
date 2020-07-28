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
import java.util.List;

@WebServlet("/")
public class MainServlet extends HttpServlet {
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

    //Получает список всех книг и отправляет его на jsp страницу
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDao.findAll();
        req.setAttribute("books", books);
        req.getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }

    //Получает id выбранной книги с jsp страницы, удаляет ее и обновляет страницу
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookDao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
