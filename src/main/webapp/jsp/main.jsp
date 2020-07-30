<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div>
        Книги:
    </div>
    <table>
        <tr>
            <th>Название книги</th>
            <th>Цена</th>
            <th>Описание</th>
            <th>Автор</th>
            <th>Продано книг</th>
            <th>Прибыль</th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.bookName}</td>
                <td>${book.price}</td>
                <td>${book.description}</td>
                <td>${book.author}</td>
                <td>${book.sold}</td>
                <td>${book.profit}</td>
                <td><a href="${pageContext.request.contextPath}/change?id=${book.id}"><button>Изменить</button></a></td>
                <td><form method="post" action="${pageContext.request.contextPath}/?id=${book.id}"><button>Удалить</button></form></td>
            </tr>
        </c:forEach>
    </table>
</div>

<a href="${pageContext.request.contextPath}/add"><button>Добавить товар</button></a>
</body>
</html>