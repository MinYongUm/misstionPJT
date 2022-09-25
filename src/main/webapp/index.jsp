<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>와이파이 정보 구하기</title>
    <style type="text/css">
        a {
            text-decoration: none
        }
    </style>
</head>
<body>
<%@ include file="/memu.jsp"%>
<br> <br>

<form action="/wifiNear.do" class="location-form">

    <label>LAT:</label>
    <label>
        <input id="x-coordinate" type="text" name="lat" placeholder="X좌표"/>
    </label>

    <label>, LNT:</label>
    <label>
        <input id="y-coordinate" type="text" name="lnt" placeholder="Y좌표"/>
    </label>

    <button id="myLocationButton" type="button">내 위치 가져오기</button>
    <button id="nearWifiButton" type="submit">근처 WIFI 정보 보기</button>
</form>

<style type="text/css">
    .tg {
        border-collapse: collapse;
        border-spacing: 0;
    }

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
        <col style="width: 76px">
        <col style="width: 111px">
        <col style="width: 82px">
        <col style="width: 98px">
        <col style="width: 127px">
        <col style="width: 91px">
        <col style="width: 106px">
        <col style="width: 85px">
        <col style="width: 102px">
        <col style="width: 89px">
        <col style="width: 91px">
        <col style="width: 112px">
        <col style="width: 104px">
        <col style="width: 118px">
        <col style="width: 78px">
        <col style="width: 65px">
        <col style="width: 92px">
    </colgroup>
    <thead>
    <tr>
        <td class="tg-c3ow">거리(Km)</td>
        <td class="tg-c3ow">관리번호</td>
        <td class="tg-c3ow">자치구</td>
        <td class="tg-c3ow">와이파이명</td>
        <td class="tg-c3ow">도로명주소</td>
        <td class="tg-c3ow">상세주소</td>
        <td class="tg-c3ow">설치위치(층)</td>
        <td class="tg-c3ow">설치유형</td>
        <td class="tg-c3ow">설치기관</td>
        <td class="tg-c3ow">서비스구분</td>
        <td class="tg-c3ow">망종류</td>
        <td class="tg-c3ow">설치년도</td>
        <td class="tg-c3ow">실내외구분</td>
        <td class="tg-c3ow">WIFI접속환경</td>
        <td class="tg-c3ow">X좌표</td>
        <td class="tg-c3ow">Y좌표</td>
        <td class="tg-c3ow">작업일자</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${searchList}" var="wifi">
        <tr>
            <th><c:out value="${wifi.distance}"/></th>
            <th><c:out value="${wifi.mgrNo}"/></th>
            <th><c:out value="${wifi.wrdofc}"/></th>
            <th><c:out value="${wifi.mainNm}"/></th>
            <th><c:out value="${wifi.adres1}"/></th>
            <th><c:out value="${wifi.adres2}"/></th>
            <th><c:out value="${wifi.floor}"/></th>
            <th><c:out value="${wifi.ty}"/></th>
            <th><c:out value="${wifi.mby}"/></th>
            <th><c:out value="${wifi.svcSe}"/></th>
            <th><c:out value="${wifi.cmcwr}"/></th>
            <th><c:out value="${wifi.year}"/></th>
            <th><c:out value="${wifi.door}"/></th>
            <th><c:out value="${wifi.remars3}"/></th>
            <th><c:out value="${wifi.lat}"/></th>
            <th><c:out value="${wifi.lnt}"/></th>
            <th><c:out value="${wifi.dttm}"/></th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript">
    class MainFunction {
        constructor() {
            this.locationForm = document.querySelector(".location-form")
            this.myLocationButton = document.querySelector("#myLocationButton")
        }

        getMyLocation() {
            const myLocationButton = this.myLocationButton;

            myLocationButton.addEventListener("click", () => {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(pos => {
                        this.locationForm["x-coordinate"].value = pos.coords.longitude;
                        this.locationForm["y-coordinate"].value = pos.coords.latitude;
                        alert('위치 정보를 불러왔습니다.')
                    })
                } else {
                    alert('Sorry, no position available.');
                }
            });
        }
    }

    document.addEventListener("DOMContentLoaded", () => {
        new MainFunction().getMyLocation();
    })
</script>

</body>
</html>