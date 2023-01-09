<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="quest" type="ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO" scope="request"/>
<%@include file="WEB-INF/pages/parts/blocks/head.jsp" %>
<%@include file="WEB-INF/pages/parts/blocks/header.jsp" %>
<div class="album py-5 bg-light">
    <div class="container">
        <%--        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">--%>
        <div class="col">
            <div class="card shadow-sm">
                <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                     xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                     preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                    <text x="50%" y="50%" fill="#eceeef" dy=".3em">${quest.name}}</text>
                </svg>
                <div class="card-body">
                    <div class="rendered-form">
                        <div class="">
                            <h1 access="false" id="control-1703961">${quest.questions.get(0).question}</h1></div>
                        <div class="formbuilder-checkbox-group form-group field-checkbox-group-1673222877891">
                            <div class="checkbox-group">
                                <form name="form" method="post" class="formbuilder-checkbox">
                                    <c:forEach begin="0" step="1" varStatus="i" items="${quest.questions.get(0).answers}"
                                               var="answer">
                                        <input name="checkbox-group-1673222877891[]" access="false"
                                               id="checkbox-group-1673222877891-0" value="option-1" type="checkbox">
                                        <label for="checkbox-group-1673222877891-0">${answer.answer}</label>
                                        <br>
                                    </c:forEach>
                                    <div class="formbuilder-button form-group field-button-1673222921723">
                                        <button type="submit" class="btn-default btn" name="button-1673222921723" access="false"
                                                id="button-1673222921723">Button
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--        </div>--%>
    </div>
</div>
<%@include file="WEB-INF/pages/parts/blocks/scripts.jsp" %>
<%@include file="WEB-INF/pages/parts/blocks/footer.jsp" %>
