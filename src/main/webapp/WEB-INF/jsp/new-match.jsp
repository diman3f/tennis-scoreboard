<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | New Match</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../tennis-scoreboard-html-layouts-main/css/style.css">

    <script src="../../tennis-scoreboard-html-layouts-main/js/app.js"></script>
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="../../tennis-scoreboard-html-layouts-main/images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="/home">Home</a>
                <a class="nav-link" href="/matches?page=1">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <div>
            <h1>Start new match</h1>
            <div class="new-match-image"></div>
            <div class="form-container center">
                <form method="post" action="/new-match">
                    <%--@declare id="playerone"--%><%--@declare id="playertwo"--%><p style="color: #ff0000;">
                        <c:if test="${requestScope.noValidDto.validDto}">
                        <p style="color: #ff0000;"> ${requestScope.noValidDto.getError("nameOne")} </p>
                        <p style="color: #ff0000;"> ${requestScope.noValidDto.getError("nameTwo")} </p>
                        <p style="color: #ff0000;"> ${requestScope.noValidDto.getError("lengthOne")} </p>
                        <p style="color: #ff0000;"> ${requestScope.noValidDto.getError("lengthTwo")} </p>
                        </c:if>

                    <label class="label-player" for="playerOne">Player ONE</label>
                    <input class="input-player" placeholder="Name" type="text" name="nameOne">
                    <label class="label-player" for="playerTwo">Player TWO</label>
                    <input class="input-player" placeholder="Name" type="text" name="nameTwo">
                    <input class="form-button" type="submit" value="Start">
                </form>
            </div>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>

