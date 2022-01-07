<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">

<jsp:include page="header.jsp"/>


  <body>

  <jsp:include page="header_form.jsp"/>

    <section class="form--steps">
      <div class="form--steps-instructions">
        <div class="form--steps-container">
          <h3>Ważne!</h3>
          <p data-step="1" class="active">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="2">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="3">
           Wybierz jedną, do
            której trafi Twoja przesyłka.
          </p>
          <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
      </div>

      <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form modelAttribute="donation" action="#" method="post">
          <!-- STEP 1: class .active is switching steps -->
          <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>


            <c:forEach items="${categories}" var="c">
              <div class="form-group form-group--checkbox">
                <label>
                  <form:checkbox
                          value="${c.id}"
                          path="categories"
                  />
                 <%-- <input type="hidden" name="_categories" value="on">--%>
                  <span class="checkbox"></span>
                  <span class="description"
                  >${c.name}</span
                  >

                </label>
              </div>
            </c:forEach>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 2 -->
          <div data-step="2">
            <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

            <div class="form-group form-group--inline">
              <label>
                Liczba 60l worków:
                <form:input id="quantity" type="number" name="bags" path="quantity" step="1" min="1"/>
               <%-- <input type="number" name="bags" step="1" min="1" />--%>
              </label>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>



          <!-- STEP 4 -->
          <div data-step="3" id="institutionResult">
            <h3>Wybierz organizacje, której chcesz pomóc:</h3>


            <c:forEach items="${institutions}" var="v">
              <div class="form-group form-group--checkbox" id="institutionResult">
                <label>
                  <form:radiobutton path="institution"  value="${v.id}" />
                  <span class="checkbox radio"></span>
                  <span class="description">
                  <div class="title" >Fundacja: "${v.name}"</div>
                  <div class="subtitle">
                    ${v.description}
                  </div>
                </span>
                </label>
              </div>
            </c:forEach>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 5 -->
          <div data-step="4">
            <h3>Podaj adres oraz termin odbioru rzeczy przez kuriera:</h3>

            <div class="form-section form-section--columns">
              <div class="form-section--column">
                <h4>Adres odbioru</h4>

                <div class="form-group form-group--inline">
                  <label> Ulica <form:input path="street"/> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label> Miasto  <form:input path="city"/> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>  Kod pocztowy  <form:input path="zipCode" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label> Numer telefonu <form:input path="phone" /> </label>
                </div>
              </div>

              <div class="form-section--column">
                <h4>Termin odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Data <form:input type="date" path="pickUpDate"/> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label> Godzina   <form:input type="time" path="pickUpTime" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Uwagi dla kuriera
                    <form:textarea path="pickUpComment"/>
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 6 -->
          <div data-step="5">
            <h3>Podsumowanie Twojej darowizny</h3>

            <div class="summary">
              <div class="form-section">
                <h4>Oddajesz:</h4>
                <ul>
                  <li>
                    <span class="icon icon-bag"></span>
                    <span class="summary--text" id="quantity-result"
                      ></span
                    >
                  </li>

                  <li>
                    <span class="icon icon-hand"></span>
                    <span class="summary--text" id="institution"
                      ></span
                    >
                  </li>
                </ul>
              </div>

              <div class="form-section form-section--columns">
                <div class="form-section--column">
                  <h4>Adres odbioru:</h4>
                  <ul>
                    <li>idProsta 51</li>
                    <li>Warszawa</li>
                    <li>99-098</li>
                    <li>123 456 789</li>
                  </ul>
                </div>

                <div class="form-section--column">
                  <h4>Termin odbioru:</h4>
                  <ul>
                    <li>13/12/2018</li>
                    <li>15:40</li>
                    <li>Brak uwag</li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="submit" class="btn">Potwierdzam</button>
            </div>
          </div>
        </form:form>
      </div>
    </section>

    <footer>
      <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form class="form--contact">
          <div class="form-group form-group--50">
            <input type="text" name="name" placeholder="Imię" />
          </div>
          <div class="form-group form-group--50">
            <input type="text" name="surname" placeholder="Nazwisko" />
          </div>

          <div class="form-group">
            <textarea
              name="message"
              placeholder="Wiadomość"
              rows="1"
            ></textarea>
          </div>

          <button class="btn" type="submit">Wyślij</button>
        </form>
      </div>
      <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
          <a href="#" class="btn btn--small"><img src="<c:url value="resources/images/icon-facebook.svg"/>"></a>
          <a href="#" class="btn btn--small"><img src="<c:url value ="resources/images/icon-instagram.svg"/>"></a>
        </div>
      </div>
    </footer>
    <script src="<c:url value="resources/js/app.js"/>"></script>
  </body>
</html>