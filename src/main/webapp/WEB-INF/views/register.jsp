<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="header.jsp"/>

    <header>
      <nav class="container container--70">
        <ul class="nav--actions">
          <li><a href="#">Zaloguj</a></li>
          <li class="highlighted"><a href="#">Załóż konto</a></li>
        </ul>

        <ul>
          <li><a href="index.html" class="btn btn--without-border active">Start</a></li>
          <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
          <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
          <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
          <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
      </nav>
    </header>

    <section class="login-page">
      <h2>Załóż konto</h2>
      <form:form method="post" modelAttribute="user">
        <div class="form-group">
          <form:input type="text" path="username" placeholder="username" />
        </div>

        <div class="form-group">
          <form:input type="email" path="email" placeholder="Email" />
        </div>
        <div class="form-group">
          <form:input type="password" path="password" placeholder="Hasło" />
        </div>

        <div class="form-group form-group--buttons">
          <a href="login.html" class="btn btn--without-border">Zaloguj się</a>
          <button class="btn" type="submit">Załóż konto</button>
        </div>
      </form:form>
    </section>

<%@include file="footer.jsp" %>

  </body>
</html>
