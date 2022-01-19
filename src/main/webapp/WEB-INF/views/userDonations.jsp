<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">


<jsp:include page="header.jsp"/>


<body>
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/userMenu" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            <li><a href="/logout" class="btn btn--small btn--without-border">Wyloguj</a></li>
            <li><a href="/addDonation" class="btn btn--small btn--highlighted">Przygotuj paczkę</a></li>
        </ul>

        <ul>
            <li><a href="/addDonation" class="btn btn--without-border active">Start</a></li>
            <li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#aboutUs" class="btn btn--without-border">O nas</a></li>
            <li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zacznij pomagać!<br/>
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>
        </div>
    </div>
</header>




<section id="help"class="stats--item">
    <h2>Twoje przekazane dary</h2>


    <c:forEach items="${userDonations}" var="d">

                  <div class="title" >Fundacja: "${d.institution}"
                  <div class="subtitle"> ilosc workow: ${d.quantity}                     <hr>

                  </div>
    </c:forEach>

</section>

<%@include file="footer.jsp" %>


