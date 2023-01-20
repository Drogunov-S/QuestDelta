<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="game" type="ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO" scope="request"/>
<%@include file="WEB-INF/pages/parts/blocks/head.jsp" %>
<%@include file="WEB-INF/pages/parts/blocks/header.jsp" %>
<div class="album py-5 bg-light">
    <div class="container">
        <div class="col">
            <div class="card shadow-sm">
                <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                     xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                     preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                    <text x="50%" y="50%" fill="#eceeef" dy=".3em">${game.quest.name}</text>
                </svg>
                <div class="card-body">
                    <c:choose>
                    <c:when test="${game.gameState != 'END'}">
                    <div class="">
                        <h1 access="false"
                            id="control-1703961">${game.lastQuestion.question}</h1></div>
                    <form name="formAnswers" method="post" class="form-answers">
                        <c:forEach begin="0" step="1" varStatus="i" items="${game.lastQuestion.answers}"
                                   var="answer">
                            <input name="checkAnswer" class="form-answers_checkbox-answer" access="false"
                                   id="checkbox-answer" value="${answer.id}" type="radio">
                            <label for="checkbox-answer">${answer.answer}</label>
                            <br>
                        </c:forEach>
                        <button type="submit" value="${game.id}" class="btn btn-save"
                                name="btnSave" access="false"
                                id="">Принять
                        </button>
                    </form>
                </div>
                <c:if test="${not empty requestScope.error}">

                    <p class="error">
                        Ошибка: ${requestScope.get("error")}
                    </p>
                </c:if>
                </c:when>
                <c:when test="${game.gameState == 'END'}">
                    <h1>Игра завершена</h1>
                </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<%@include file="WEB-INF/pages/parts/blocks/scripts.jsp" %>
<%@include file="WEB-INF/pages/parts/blocks/footer.jsp" %>
