<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
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
            <span class="logo-text">${requestScope.get("id")}</span>
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
        <h1>Matches</h1>
        <div class="input-container">
            <form method="GET" action="/matches">
                <input class="input-filter" placeholder="Filter by name" type="text" name="name" required/>
                <input type="submit" style="display:none"></form>
            <div class="form-container center">
            </div>
            <div>
                <a href="#">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>${requestScope.get("playerOne")}</th>
                <th>${requestScope.get("playerTwo")}</th>
                <th>${requestScope.get("winner")}</th>
            </tr>
            <tr>
                <td>${requestScope.get("playerOne")}</td>
                <td>${requestScope.get("playerTwo")}</td>
                <td><span class="winner-name-td">${requestScope.get("winner")}</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Roger Federer</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Rafael Nadal</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Roger Federer</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Rafael Nadal</span></td>
            </tr>
        </table>

        <div class="pagination">
            <a class="prev" href="#"> < </a>
            <a class="num-page current" href="/matches?page=1">1</a>
            <a class="num-page" href="/matches?page=2">2</a>
            <a class="num-page" href="/matches?page=3">3</a>
            <a class="next" href="#"> > </a>
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
