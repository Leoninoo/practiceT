<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div>
        <h1>Изменить книгу</h1>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/change?id=${book.id}">
        <label for="name">Название книги
            <input class="input-field" type="text" id="name" name="name" value="${book.bookName}">
        </label>
        <label for="price">Цена
            <input class="input-field" type="number" id="price" name="price" value="${book.price}">
        </label>
        <label for="description">Описание
            <input class="input-field" type="text" id="description" name="description" value="${book.description}">
        </label>
        <label for="author">Автор
            <input class="input-field" type="text" id="author" name="author" value="${book.author}">
        </label>
        <label for="sold">Продано книг
            <input class="input-field" type="number" id="sold" name="sold" value="${book.sold}">
        </label>
        <input type="submit" value="Изменить">
    </form>
</div>
</body>
</html>