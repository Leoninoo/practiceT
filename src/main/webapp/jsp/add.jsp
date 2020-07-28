<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div>
        <h1>Добавить книгу</h1>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/add">
        <label for="name">Название книги
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="price">Цена
            <input class="input-field" type="number" id="price" name="price" value="0">
        </label>
        <label for="description">Описание
            <input class="input-field" type="text" id="description" name="description">
        </label>
        <label for="author">Автор
            <input class="input-field" type="text" id="author" name="author">
        </label>
        <input type="submit" value="Добавить">
    </form>
</div>
</body>
</html>