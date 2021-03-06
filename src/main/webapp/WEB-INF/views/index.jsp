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
        </ul>

        <ul>
            <li><a href="resources/css/style.css" class="btn btn--without-border active">Start</a></li>
            <li><a href="resources/css/style.css" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="resources/css/style.css" class="btn btn--without-border">O nas</a></li>
            <li><a href="resources/css/style.css" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="resources/css/style.css" class="btn btn--without-border">Kontakt</a></li>
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

<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>${sumOfQuantities}</em>

            <h3>Oddanych worków</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
                tempora!</p>
        </div>

        <div class="stats--item">
            <em>${sumOfDonations}</em>
            <h3>Przekazanych darów</h3>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
                quam.</p>
        </div>

    </div>
</section>

<section class="steps">
    <h2>Wystarczą 4 proste kroki</h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3>Wybierz rzeczy</h3>
            <p>ubrania, zabawki, sprzęt i inne</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3>Spakuj je</h3>
            <p>skorzystaj z worków na śmieci</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3>Zdecyduj komu chcesz pomóc</h3>
            <p>wybierz zaufane miejsce</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3>Zamów kuriera</h3>
            <p>kurier przyjedzie w dogodnym terminie</p>
        </div>
    </div>

    <a href="/register" class="btn btn--large">Załóż konto</a>
</section>

<section class="about-us">
    <div class="about-us--text">
        <h2>O nas</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas vitae animi rem pariatur incidunt libero
            optio esse quisquam illo omnis.</p>
        <img src="<c:url value="resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
    </div>
    <div class="about-us--image"><img src="<c:url value="resources/images/about-us.jpg"/>" alt="People in circle"/>
    </div>
</section>

<section class="help">
    <h2>Komu pomagamy?</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy.
            Możesz sprawdzić czym się zajmują.</p>


        <ul class="help--slides-items">
            <c:forEach items="${institutions}" var="i" step="2" varStatus="loopStatus">
                <li>
                    <div class="col">
                        <div class="title">Fundacja "${institutions[loopStatus.index].name}"</div>
                        <div class="subtitle">"${institutions[loopStatus.index].description}"</div>
                    </div>

                    <c:choose>
                        <c:when test="${institutions[loopStatus.index+1].name != null}">
                            <div class="col">
                                <div class="title">Fundacja "${institutions[loopStatus.index+1].name}"</div>
                                <div class="subtitle">"${institutions[loopStatus.index+1].description}"</div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="">
                            </div>
                        </c:otherwise>
                    </c:choose>

                </li>
            </c:forEach>
        </ul>
    </div>

</section>

<%@include file="footer.jsp" %>


