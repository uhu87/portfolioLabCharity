<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="header.jsp"/>



    <section class="login-page">
      <h2>Załóż konto</h2>
      <form:form>
        <div class="form-group">
          <input type="text" path="username" placeholder="user" />
        </div>

        <div class="form-group">
          <input type="email" path="email" placeholder="Email" />
        </div>
        <div class="form-group">
          <input type="password" path="password" placeholder="Hasło" />
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
