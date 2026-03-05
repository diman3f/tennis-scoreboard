`<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tennis-scoreboard-html-layouts-main/css/style.css">

    <script src="${pageContext.request.contextPath}/tennis-scoreboard-html-layouts-main/js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="${pageContext.request.contextPath}/tennis-scoreboard-html-layouts-main/images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">${requestScope.get("id")}</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches?page=1">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <form method="GET" action="${pageContext.request.contextPath}/matches">
                <input type="text" name="page" value="1" style="display:none" >
                <input class="input-filter" placeholder="Filter by name" type="text" name=filter_by_player_name required/>
                <input type="submit" style="display:none"></form>
            <div class="form-container center">
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/matches?page=1">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>

        </div>
        <table class="table-matches">
            <c:forEach items="${requestScope.matches}" var="match">
                <tr>
                    <td>${match.namePlayerOne}</td>
                    <td>${match.namePlayerTwo}</td>
                    <td><span class="winner-name-td">${match.winner}</span></td>
                </tr>
            </c:forEach>
        </table>

        <div class="pagination">
            <c:if test="${requestScope.currentPage != 1}">
                <a class="prev" href="${pageContext.request.contextPath}/matches?page=${requestScope.currentPage - 1}">Previous< </a>
            </c:if>

            <c:forEach begin="1" end="${requestScope.noOfPage}" var="i">
                <c:choose>
                    <c:when test="${requestScope.currentPage eq i}">
                        <a class="num-page current">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a class="num-page current" href="${pageContext.request.contextPath}/matches?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.currentPage lt requestScope.noOfPage}">
                <a class="next" href="${pageContext.request.contextPath}/matches?page=${requestScope.currentPage + 1}"> Next </a>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
