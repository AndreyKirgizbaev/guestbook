<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
    <title>GuestBook</title>
</head>


<body>

<c:url value="/index" var="jdbcQueryAllGuests"/>
<c:url value="/jdbcInsertGuest" var="jdbcInsertGuest"/>

<div>
    <h1>
        GuestBook
    </h1>

<%--    <a href="${jdbcQueryAllGuests}">GuestBook</a>--%>


    <div>
        <c:if test="${not empty resultObject}">

            <table>
                <c:forEach var="guestVar" items="#{resultObject}">
                    <tr>
                        <td><b>Guest: </b></td>
                        <td><c:out value="${guestVar.guestName}"/></td>
                    </tr>
                    <tr>
                        <td><b>Comment: </b></td>
                        <td><c:out value="${guestVar.comment}"/></td>
                    </tr>
                    <tr>
                        <td><br/></td>
                    </tr>
                </c:forEach>
            </table>

        </c:if>
    </div>

    <br/>


    <form name="guest" action="/jdbcInsertGuest" method="POST">
        <table>
            <tr>
                <td><b>Name: </b></td>
                <td><input type='text' name='guestName' required/></td>
            </tr>
            <tr>
                <td><b>Comment: </b></td>
                <td><input type='text' name="comment" required/></td>
            </tr>
        </table>

        <input type="submit" value="Send"/>
    </form>

</div>

</body>

</html>
