<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2022-09-07
  Time: 오전 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <h1>위치 히스토리 목록</h1>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <title>위치 히스토리 목록</title>
</head>
<body>
<%@ include file="/memu.jsp" %>

<style type="text/css">
    .tg td {
        border-color: black;
        border-style: solid;
        border-width: 1px;
        font-family: Arial, sans-serif;
        font-size: 14px;
        color: white;
        overflow: hidden;
        padding: 10px 5px;
        word-break: normal;
        background: #0f6702;
    }

    .tg th {
        border-color: black;
        border-style: solid;
        border-width: 1px;
        font-family: Arial, sans-serif;
        font-size: 14px;
        font-weight: normal;
        overflow: hidden;
        padding: 10px 5px;
        word-break: normal;
        background: #ffffe2;
    }

    .tg .tg-c3ow {
        border-color: inherit;
        text-align: center;
        vertical-align: top
    }
</style>
<table class="tg" style=" table-layout: fixed; width: 1627px">
    <colgroup>
        <col style="width: 50px">
        <col style="width: 100px">
        <col style="width: 100px">
        <col style="width: 100px">
        <col style="width: 50px">
    </colgroup>
    <thead>
    <tr>
        <td class="tg-c3ow">ID</td>
        <td class="tg-c3ow">x좌표</td>
        <td class="tg-c3ow">y좌표</td>
        <td class="tg-c3ow">조회일자</td>
        <td class="tg-c3ow">비고</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="history">
        <tr>
            <th><c:out value="${history.id}"/></th>
            <th><c:out value="${history.lat}"/></th>
            <th><c:out value="${history.lnt}"/></th>
            <th><c:out value="${history.date}"/></th>
            <th>
                <button type="button" class="button">삭제</button>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    $(".button").click(function () {
        var checkBtn = $(this);
        var tr = checkBtn.parent().parent();
        var td = tr.children();

        var deleteId = td.eq(0).text();

        //서블릿 호출
        $.ajax({
            type: "post",
            url: "http://localhost:8080/historyDelete.do?deleteId=" + deleteId

        }).done(function () {
            location.reload();
        })
    })

</script>

</body>
</html>